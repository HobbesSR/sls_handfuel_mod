package cardenergy.cards;

import basemod.abstracts.CustomCard;
import cardenergy.CardEnergyMod;
import cardenergy.character.CardEnergyCharacterEnum;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public final class TerracottaCardHelper {
    private static final int SMALL_ART_WIDTH = 250;
    private static final int SMALL_ART_HEIGHT = 190;
    private static final Map<String, Texture> textureCache = new HashMap<>();
    private static final Field portraitImgField = findPortraitImgField();

    private TerracottaCardHelper() {
    }

    public static void applyIdentity(AbstractCard card, String id) {
        CardStrings strings = CardCrawlGame.languagePack.getCardStrings(id);
        card.cardID = id;
        card.name = strings.NAME;
        card.originalName = strings.NAME;
        card.rawDescription = strings.DESCRIPTION;
        card.color = CardEnergyCharacterEnum.TERRACOTTA;
        applyCustomImage(card, id);
        card.initializeDescription();
    }

    public static void applyIdentityKeepBaseStrings(AbstractCard card, String id) {
        card.cardID = id;
        card.color = CardEnergyCharacterEnum.TERRACOTTA;
        applyCustomOrSourceImage(card, id, null);
        card.initializeDescription();
    }

    public static void applyIdentityKeepBaseStrings(AbstractCard card, String id, AbstractCard sourceCard) {
        card.cardID = id;
        card.color = CardEnergyCharacterEnum.TERRACOTTA;
        applyCustomOrSourceImage(card, id, sourceCard);
        card.initializeDescription();
    }

    private static void applyCustomImage(AbstractCard card, String id) {
        applyCustomOrSourceImage(card, id, null);
    }

    private static void applyCustomOrSourceImage(AbstractCard card, String id, AbstractCard sourceCard) {
        String shortId = getShortId(id);
        String rawPortraitPath = CardEnergyMod.CARD_IMG_DIR + "/raw/" + shortId + ".png";
        String imagePath = CardEnergyMod.CARD_IMG_DIR + "/" + shortId + ".png";
        String portraitPath = CardEnergyMod.CARD_IMG_DIR + "/" + shortId + "_p.png";
        String maskPath = getMaskPath(card);

        Texture maskedPortraitTexture = loadMaskedTexture(
                rawPortraitPath,
                maskPath,
                500,
                380,
                rawPortraitPath + "|" + maskPath + "|portrait"
        );
        if (maskedPortraitTexture != null) {
            Texture smallTexture = loadMaskedTexture(
                    rawPortraitPath,
                    maskPath,
                    SMALL_ART_WIDTH,
                    SMALL_ART_HEIGHT,
                    rawPortraitPath + "|" + maskPath + "|small"
            );
            applyLoadedTextures(card, imagePath, smallTexture, maskedPortraitTexture);
            return;
        }

        Texture smallTexture = loadTextureIfPresent(imagePath);
        if (smallTexture == null) {
            if (sourceCard != null) {
                Texture sourceTexture = loadSourceCardTexture(sourceCard);
                if (sourceTexture != null) {
                    applyLoadedTextures(card, imagePath, sourceTexture, sourceTexture);
                }
            }
            return;
        }

        Texture portraitTexture = loadTextureIfPresent(portraitPath);
        if (portraitTexture == null) {
            portraitTexture = smallTexture;
        }

        applyLoadedTextures(card, imagePath, smallTexture, portraitTexture);
    }

    private static void applyLoadedTextures(AbstractCard card, String assetPath, Texture smallTexture, Texture portraitTexture) {
        card.assetUrl = assetPath;
        card.portrait = new TextureAtlas.AtlasRegion(
                smallTexture,
                0,
                0,
                smallTexture.getWidth(),
                smallTexture.getHeight()
        );

        if (card instanceof CustomCard) {
            ((CustomCard) card).textureImg = null;
        }

        if (portraitImgField != null) {
            try {
                portraitImgField.set(card, portraitTexture);
            } catch (IllegalAccessException ignored) {
            }
        }
    }

    private static String getMaskPath(AbstractCard card) {
        if (card.type == AbstractCard.CardType.POWER) {
            return CardEnergyMod.CARD_IMG_DIR + "/masks/power_mask_rgba.png";
        }
        if (card.type == AbstractCard.CardType.SKILL) {
            return CardEnergyMod.CARD_IMG_DIR + "/masks/skill_mask_rgba.png";
        }
        if (card.type == AbstractCard.CardType.ATTACK) {
            return CardEnergyMod.CARD_IMG_DIR + "/masks/attack_mask_rgba.png";
        }
        return null;
    }

    private static String getShortId(String id) {
        int separator = id.indexOf(':');
        return separator >= 0 ? id.substring(separator + 1) : id;
    }

    private static Texture loadTextureIfPresent(String path) {
        if (textureCache.containsKey(path)) {
            return textureCache.get(path);
        }

        InputStream stream = TerracottaCardHelper.class.getClassLoader().getResourceAsStream(path);
        if (stream == null) {
            textureCache.put(path, null);
            return null;
        }

        try (InputStream input = stream) {
            byte[] data = readAllBytes(input);
            Pixmap pixmap = new Pixmap(data, 0, data.length);
            Texture texture = new Texture(pixmap);
            pixmap.dispose();
            textureCache.put(path, texture);
            return texture;
        } catch (IOException e) {
            throw new RuntimeException("Failed reading image resource: " + path, e);
        }
    }

    private static Texture loadMaskedTexture(String rawPortraitPath, String maskPath, int targetWidth, int targetHeight, String cacheKey) {
        if (textureCache.containsKey(cacheKey)) {
            return textureCache.get(cacheKey);
        }

        Pixmap source = loadPixmapIfPresent(rawPortraitPath);
        if (source == null) {
            textureCache.put(cacheKey, null);
            return null;
        }

        Pixmap masked = new Pixmap(source.getWidth(), source.getHeight(), Pixmap.Format.RGBA8888);
        try {
            if (maskPath == null) {
                masked.drawPixmap(source, 0, 0);
            } else {
                Pixmap mask = loadPixmapIfPresent(maskPath);
                if (mask == null) {
                    masked.drawPixmap(source, 0, 0);
                } else {
                    try {
                        applyAlphaMask(source, mask, masked);
                    } finally {
                        mask.dispose();
                    }
                }
            }
            Pixmap finalPixmap = masked;
            if (masked.getWidth() != targetWidth || masked.getHeight() != targetHeight) {
                finalPixmap = new Pixmap(targetWidth, targetHeight, Pixmap.Format.RGBA8888);
                finalPixmap.drawPixmap(masked, 0, 0, masked.getWidth(), masked.getHeight(), 0, 0, targetWidth, targetHeight);
            }

            Texture texture = new Texture(finalPixmap);
            if (finalPixmap != masked) {
                finalPixmap.dispose();
            }
            textureCache.put(cacheKey, texture);
            return texture;
        } finally {
            masked.dispose();
            source.dispose();
        }
    }

    private static Pixmap loadPixmapIfPresent(String path) {
        InputStream stream = TerracottaCardHelper.class.getClassLoader().getResourceAsStream(path);
        if (stream == null) {
            return null;
        }

        try (InputStream input = stream) {
            byte[] data = readAllBytes(input);
            return new Pixmap(data, 0, data.length);
        } catch (IOException e) {
            throw new RuntimeException("Failed reading image resource: " + path, e);
        }
    }

    private static void applyAlphaMask(Pixmap source, Pixmap mask, Pixmap destination) {
        int width = Math.min(source.getWidth(), mask.getWidth());
        int height = Math.min(source.getHeight(), mask.getHeight());
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int srcPixel = source.getPixel(x, y);
                int maskPixel = mask.getPixel(x, y);
                int srcAlpha = srcPixel & 0x000000ff;
                int maskAlpha = maskPixel & 0x000000ff;
                int combinedAlpha = (srcAlpha * maskAlpha) / 255;
                int combinedPixel = (srcPixel & 0xffffff00) | combinedAlpha;
                destination.drawPixel(x, y, combinedPixel);
            }
        }
    }

    private static byte[] readAllBytes(InputStream stream) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int read;
        while ((read = stream.read(buffer)) != -1) {
            output.write(buffer, 0, read);
        }
        return output.toByteArray();
    }

    private static Field findPortraitImgField() {
        try {
            Field field = AbstractCard.class.getDeclaredField("portraitImg");
            field.setAccessible(true);
            return field;
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    private static Texture loadSourceCardTexture(AbstractCard sourceCard) {
        if (sourceCard.assetUrl == null || sourceCard.assetUrl.isEmpty()) {
            return null;
        }
        String normalized = sourceCard.assetUrl.endsWith(".png")
                ? sourceCard.assetUrl
                : sourceCard.assetUrl + ".png";
        String fullPath = normalized.startsWith("images/")
                ? normalized
                : "images/cards/" + normalized;
        return loadTextureIfPresent(fullPath);
    }
}

