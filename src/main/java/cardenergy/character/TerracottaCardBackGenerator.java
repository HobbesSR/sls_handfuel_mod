package cardenergy.character;

import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public final class TerracottaCardBackGenerator {
    private static final String BASEMOD_TEST_PREFIX = "img/test/test/512/";
    private static final String BASEMOD_ATTACK = BASEMOD_TEST_PREFIX + "bg_attack_purple.png";
    private static final String BASEMOD_SKILL = BASEMOD_TEST_PREFIX + "bg_skill_purple.png";
    private static final String BASEMOD_POWER = BASEMOD_TEST_PREFIX + "bg_power_purple.png";
    private static final String BASEMOD_ORB = BASEMOD_TEST_PREFIX + "card_purple_orb.png";

    private static final float TARGET_HUE = 18.0f / 360.0f;
    private static final float CARD_SATURATION_SCALE = 0.82f;
    private static final float ORB_SATURATION_SCALE = 1.00f;
    private static final float CARD_VALUE_SCALE = 0.96f;
    private static final float ORB_VALUE_SCALE = 0.96f;
    private static final int LARGE_CARD_DIMENSION = 1024;
    private static final int LARGE_ORB_DIMENSION = 164;
    private static final double ORB_CARD_SCALE = 0.57;
    private static final double ORB_CARD_BASE_ANGLE_DEGREES = 0.0;
    private static final double ORB_ANCHOR_Y_RATIO = 0.82;

    private TerracottaCardBackGenerator() {
    }

    public static GeneratedPaths generate() {
        File outputDir = new File(System.getProperty("java.io.tmpdir"), "cardenergy/terracotta_cardback");
        if (!outputDir.exists() && !outputDir.mkdirs()) {
            throw new RuntimeException("Failed to create Terracotta cardback directory: " + outputDir);
        }

        File baseModJar = resolveBaseModJar();
        File gameJar = resolveGameJar();
        try (ZipFile baseModZip = new ZipFile(baseModJar); ZipFile gameZip = new ZipFile(gameJar)) {
            BufferedImage attackSource = readImage(baseModZip, BASEMOD_ATTACK);
            BufferedImage skillSource = readImage(baseModZip, BASEMOD_SKILL);
            BufferedImage powerSource = readImage(baseModZip, BASEMOD_POWER);
            BufferedImage orbTemplate = readImage(baseModZip, BASEMOD_ORB);
            File attack = generateFlat(outputDir, "bg_attack.png", attackSource, 0);
            File skill = generateFlat(outputDir, "bg_skill.png", skillSource, 0);
            File power = generateFlat(outputDir, "bg_power.png", powerSource, 0);
            File orb = generateCompositeOrb(outputDir, "energy_orb.png", 512, attackSource, skillSource, powerSource, orbTemplate);

            File attackPortrait = generateScaled(outputDir, "bg_attack_p.png", attackSource, LARGE_CARD_DIMENSION);
            File skillPortrait = generateScaled(outputDir, "bg_skill_p.png", skillSource, LARGE_CARD_DIMENSION);
            File powerPortrait = generateScaled(outputDir, "bg_power_p.png", powerSource, LARGE_CARD_DIMENSION);
            File orbPortrait = generateCompositeOrb(outputDir, "energy_orb_p.png", LARGE_ORB_DIMENSION, attackSource, skillSource, powerSource, null);
            File smallOrb = generateSmallOrb(outputDir, gameZip);

            return new GeneratedPaths(
                    attack.getAbsolutePath(),
                    skill.getAbsolutePath(),
                    power.getAbsolutePath(),
                    orb.getAbsolutePath(),
                    attackPortrait.getAbsolutePath(),
                    skillPortrait.getAbsolutePath(),
                    powerPortrait.getAbsolutePath(),
                    orbPortrait.getAbsolutePath(),
                    smallOrb.getAbsolutePath()
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed generating Terracotta cardback assets", e);
        }
    }

    private static File resolveGameJar() {
        File fromWorkingDirectory = new File("desktop-1.0.jar");
        if (fromWorkingDirectory.isFile()) {
            return fromWorkingDirectory;
        }

        String classPath = System.getProperty("java.class.path", "");
        String[] entries = classPath.split(File.pathSeparator);
        for (String entry : entries) {
            if (entry == null || entry.isEmpty()) {
                continue;
            }
            File candidate = new File(entry);
            if (candidate.isFile() && "desktop-1.0.jar".equalsIgnoreCase(candidate.getName())) {
                return candidate;
            }
        }

        throw new RuntimeException("Failed to resolve Slay the Spire game jar from working directory or classpath");
    }

    private static File resolveBaseModJar() {
        String candidate = System.getProperty("basemod.jar.path");
        if (candidate != null) {
            File file = new File(candidate);
            if (file.isFile()) {
                return file;
            }
        }
        File fromGameMods = new File("mods/BaseMod.jar");
        if (fromGameMods.isFile()) {
            return fromGameMods;
        }
        throw new RuntimeException("Failed to resolve BaseMod.jar");
    }

    private static BufferedImage readImage(ZipFile zip, String entryName) throws IOException {
        ZipEntry entry = zip.getEntry(entryName);
        if (entry == null) {
            throw new IllegalStateException("Missing generator source asset: " + entryName);
        }
        try (InputStream in = zip.getInputStream(entry)) {
            return ImageIO.read(in);
        }
    }

    private static File generateFlat(File outputDir, String fileName, BufferedImage source, int pad) throws IOException {
        BufferedImage transformed = transformCardImage(source);
        if (pad <= 0) {
            File output = new File(outputDir, fileName);
            ImageIO.write(transformed, "png", output);
            return output;
        }

        BufferedImage padded = new BufferedImage(
                transformed.getWidth() + pad * 2,
                transformed.getHeight() + pad * 2,
                BufferedImage.TYPE_INT_ARGB
        );
        Graphics2D graphics = padded.createGraphics();
        try {
            graphics.drawImage(transformed, pad, pad, null);
        } finally {
            graphics.dispose();
        }

        File output = new File(outputDir, fileName);
        ImageIO.write(padded, "png", output);
        return output;
    }

    private static File generateScaled(File outputDir, String fileName, BufferedImage source, int targetSize) throws IOException {
        BufferedImage transformed = transformCardImage(source);
        BufferedImage scaled = new BufferedImage(targetSize, targetSize, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = scaled.createGraphics();
        try {
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics.drawImage(transformed, 0, 0, targetSize, targetSize, null);
        } finally {
            graphics.dispose();
        }
        File output = new File(outputDir, fileName);
        ImageIO.write(scaled, "png", output);
        return output;
    }

    private static File generateCompositeOrb(
            File outputDir,
            String fileName,
            int targetSize,
            BufferedImage attackSource,
            BufferedImage skillSource,
            BufferedImage powerSource,
            BufferedImage orbTemplate
    ) throws IOException {
        BufferedImage composite = orbTemplate != null
                ? new BufferedImage(orbTemplate.getWidth(), orbTemplate.getHeight(), BufferedImage.TYPE_INT_ARGB)
                : new BufferedImage(targetSize, targetSize, BufferedImage.TYPE_INT_ARGB);
        OrbLayout layout = orbTemplate != null
                ? findOccupiedBounds(orbTemplate)
                : new OrbLayout(0, 0, targetSize, targetSize);
        int layoutSize = Math.min(layout.width, layout.height);
        BufferedImage[] transformedCards = new BufferedImage[] {
                scaleImage(transformOrbMiniCardImage(attackSource), layoutSize),
                scaleImage(transformOrbMiniCardImage(skillSource), layoutSize),
                scaleImage(transformOrbMiniCardImage(powerSource), layoutSize)
        };

        Graphics2D graphics = composite.createGraphics();
        try {
            graphics.setComposite(AlphaComposite.SrcOver);
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            double centerX = layout.centerX();
            double centerY = layout.centerY();
            for (int i = 0; i < 6; i++) {
                BufferedImage card = transformedCards[i % transformedCards.length];
                double rotationDegrees = ORB_CARD_BASE_ANGLE_DEGREES + (60.0 * i);
                drawAnchoredCard(graphics, card, centerX, centerY, rotationDegrees);
            }
        } finally {
            graphics.dispose();
        }

        File output = new File(outputDir, fileName);
        ImageIO.write(composite, "png", output);
        return output;
    }

    private static BufferedImage scaleImage(BufferedImage source, int targetSize) {
        int scaledWidth = Math.max(1, (int) Math.round(targetSize * ORB_CARD_SCALE));
        int scaledHeight = Math.max(1, (int) Math.round(((double) source.getHeight() / source.getWidth()) * scaledWidth));
        BufferedImage scaled = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = scaled.createGraphics();
        try {
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics.drawImage(source, 0, 0, scaledWidth, scaledHeight, null);
        } finally {
            graphics.dispose();
        }
        return scaled;
    }

    private static void drawAnchoredCard(Graphics2D graphics, BufferedImage card, double centerX, double centerY, double rotationDegrees) {
        double anchorX = card.getWidth() / 2.0;
        double anchorY = card.getHeight() * ORB_ANCHOR_Y_RATIO;
        AffineTransform transform = new AffineTransform();
        transform.translate(centerX, centerY);
        transform.rotate(Math.toRadians(rotationDegrees));
        transform.translate(-anchorX, -anchorY);
        graphics.drawImage(card, transform, null);
    }

    private static File generateSmallOrb(File outputDir, ZipFile gameZip) throws IOException {
        ZipEntry entry = gameZip.getEntry("images/cards/small_orb.png");
        if (entry == null) {
            BufferedImage fallback = new BufferedImage(24, 24, BufferedImage.TYPE_INT_ARGB);
            File output = new File(outputDir, "small_orb.png");
            ImageIO.write(fallback, "png", output);
            return output;
        }
        try (InputStream in = gameZip.getInputStream(entry)) {
            BufferedImage source = ImageIO.read(in);
            BufferedImage transformed = transformCardImage(source);
            File output = new File(outputDir, "small_orb.png");
            ImageIO.write(transformed, "png", output);
            return output;
        }
    }

    private static BufferedImage transformCardImage(BufferedImage source) {
        return transformImage(source, CARD_SATURATION_SCALE, CARD_VALUE_SCALE);
    }

    private static BufferedImage transformOrbMiniCardImage(BufferedImage source) {
        return transformImage(source, ORB_SATURATION_SCALE, ORB_VALUE_SCALE);
    }

    private static BufferedImage transformImage(BufferedImage source, float saturationScale, float valueScale) {
        BufferedImage transformed = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                transformed.setRGB(x, y, transformPixel(source.getRGB(x, y), saturationScale, valueScale));
            }
        }
        return transformed;
    }

    private static OrbLayout findOccupiedBounds(BufferedImage source) {
        int minX = source.getWidth();
        int minY = source.getHeight();
        int maxX = -1;
        int maxY = -1;
        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                int alpha = (source.getRGB(x, y) >>> 24) & 0xff;
                if (alpha == 0) {
                    continue;
                }
                if (x < minX) {
                    minX = x;
                }
                if (y < minY) {
                    minY = y;
                }
                if (x > maxX) {
                    maxX = x;
                }
                if (y > maxY) {
                    maxY = y;
                }
            }
        }

        if (maxX < minX || maxY < minY) {
            return new OrbLayout(0, 0, source.getWidth(), source.getHeight());
        }
        return new OrbLayout(minX, minY, maxX - minX + 1, maxY - minY + 1);
    }

    private static int transformPixel(int argb, float saturationScale, float valueScale) {
        int a = (argb >>> 24) & 0xff;
        int r = (argb >>> 16) & 0xff;
        int g = (argb >>> 8) & 0xff;
        int b = argb & 0xff;

        if (a == 0) {
            return 0;
        }

        float[] hsv = java.awt.Color.RGBtoHSB(r, g, b, null);
        float hue = TARGET_HUE;
        float saturation = clamp01(hsv[1] * saturationScale);
        float value = clamp01(hsv[2] * valueScale);
        int rgb = java.awt.Color.HSBtoRGB(hue, saturation, value);
        return (a << 24)
                | (((rgb >> 16) & 0xff) << 16)
                | (((rgb >> 8) & 0xff) << 8)
                | (rgb & 0xff);
    }

    private static float clamp01(float value) {
        return Math.max(0.0f, Math.min(1.0f, value));
    }

    public static final class GeneratedPaths {
        public final String attackBg;
        public final String skillBg;
        public final String powerBg;
        public final String orb;
        public final String attackBgPortrait;
        public final String skillBgPortrait;
        public final String powerBgPortrait;
        public final String orbPortrait;
        public final String smallOrb;

        private GeneratedPaths(
                String attackBg,
                String skillBg,
                String powerBg,
                String orb,
                String attackBgPortrait,
                String skillBgPortrait,
                String powerBgPortrait,
                String orbPortrait,
                String smallOrb
        ) {
            this.attackBg = attackBg;
            this.skillBg = skillBg;
            this.powerBg = powerBg;
            this.orb = orb;
            this.attackBgPortrait = attackBgPortrait;
            this.skillBgPortrait = skillBgPortrait;
            this.powerBgPortrait = powerBgPortrait;
            this.orbPortrait = orbPortrait;
            this.smallOrb = smallOrb;
        }
    }

    private static final class OrbLayout {
        private final int x;
        private final int y;
        private final int width;
        private final int height;

        private OrbLayout(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        private double centerX() {
            return x + (width / 2.0);
        }

        private double centerY() {
            return y + (height / 2.0);
        }
    }
}
