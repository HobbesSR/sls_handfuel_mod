package cardenergy.character;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
    private static final float SATURATION_SCALE = 0.82f;
    private static final float VALUE_SCALE = 0.96f;
    private static final int LARGE_CARD_DIMENSION = 1024;
    private static final int LARGE_ORB_DIMENSION = 164;

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
            File attack = generateFlat(outputDir, "bg_attack.png", readImage(baseModZip, BASEMOD_ATTACK), 0);
            File skill = generateFlat(outputDir, "bg_skill.png", readImage(baseModZip, BASEMOD_SKILL), 0);
            File power = generateFlat(outputDir, "bg_power.png", readImage(baseModZip, BASEMOD_POWER), 0);
            File orb = generateFlat(outputDir, "energy_orb.png", readImage(baseModZip, BASEMOD_ORB), 0);

            File attackPortrait = generateScaled(outputDir, "bg_attack_p.png", readImage(baseModZip, BASEMOD_ATTACK), LARGE_CARD_DIMENSION);
            File skillPortrait = generateScaled(outputDir, "bg_skill_p.png", readImage(baseModZip, BASEMOD_SKILL), LARGE_CARD_DIMENSION);
            File powerPortrait = generateScaled(outputDir, "bg_power_p.png", readImage(baseModZip, BASEMOD_POWER), LARGE_CARD_DIMENSION);
            File orbPortrait = generateScaled(outputDir, "energy_orb_p.png", readImage(baseModZip, BASEMOD_ORB), LARGE_ORB_DIMENSION);
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
        BufferedImage transformed = transformImage(source);
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
        BufferedImage transformed = transformImage(source);
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
            BufferedImage transformed = transformImage(source);
            File output = new File(outputDir, "small_orb.png");
            ImageIO.write(transformed, "png", output);
            return output;
        }
    }

    private static BufferedImage transformImage(BufferedImage source) {
        BufferedImage transformed = new BufferedImage(source.getWidth(), source.getHeight(), BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < source.getHeight(); y++) {
            for (int x = 0; x < source.getWidth(); x++) {
                transformed.setRGB(x, y, transformPixel(source.getRGB(x, y)));
            }
        }
        return transformed;
    }

    private static int transformPixel(int argb) {
        int a = (argb >>> 24) & 0xff;
        int r = (argb >>> 16) & 0xff;
        int g = (argb >>> 8) & 0xff;
        int b = argb & 0xff;

        if (a == 0) {
            return 0;
        }

        float[] hsv = java.awt.Color.RGBtoHSB(r, g, b, null);
        float hue = TARGET_HUE;
        float saturation = clamp01(hsv[1] * SATURATION_SCALE);
        float value = clamp01(hsv[2] * VALUE_SCALE);
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
}
