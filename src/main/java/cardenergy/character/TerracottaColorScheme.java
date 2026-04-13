package cardenergy.character;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;

public final class TerracottaColorScheme {
    public static final Color TERRACOTTA = new Color(0.74F, 0.41F, 0.28F, 1.0F);
    private static final Color TERRACOTTA_BG = new Color(0.36F, 0.18F, 0.12F, 1.0F);
    private static final Color TERRACOTTA_BACK = new Color(0.57F, 0.30F, 0.19F, 1.0F);
    private static final Color TERRACOTTA_FRAME = new Color(0.84F, 0.54F, 0.37F, 1.0F);
    private static final Color TERRACOTTA_OUTLINE = new Color(0.16F, 0.08F, 0.05F, 1.0F);
    private static final Color TERRACOTTA_DESC = new Color(0.45F, 0.25F, 0.17F, 1.0F);
    private static final Color TERRACOTTA_GLOW = new Color(0.95F, 0.75F, 0.58F, 1.0F);

    private TerracottaColorScheme() {
    }

    public static void register() {
        TerracottaCardBackGenerator.GeneratedPaths paths = TerracottaCardBackGenerator.generate();
        BaseMod.addColor(
                CardEnergyCharacterEnum.TERRACOTTA,
                TERRACOTTA_BG,
                TERRACOTTA_BACK,
                TERRACOTTA_FRAME,
                TERRACOTTA_OUTLINE,
                TERRACOTTA_DESC,
                TERRACOTTA,
                TERRACOTTA_GLOW,
                paths.attackBg,
                paths.skillBg,
                paths.powerBg,
                paths.orb,
                paths.attackBgPortrait,
                paths.skillBgPortrait,
                paths.powerBgPortrait,
                paths.orbPortrait,
                paths.smallOrb
        );
    }
}
