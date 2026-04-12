package cardenergy.character;

import basemod.BaseMod;
import cardenergy.CardEnergyMod;
import com.badlogic.gdx.graphics.Color;

public final class TerracottaColorScheme {
    public static final Color TERRACOTTA = new Color(0.74F, 0.41F, 0.28F, 1.0F);
    private static final Color TERRACOTTA_BG = new Color(0.36F, 0.18F, 0.12F, 1.0F);
    private static final Color TERRACOTTA_BACK = new Color(0.57F, 0.30F, 0.19F, 1.0F);
    private static final Color TERRACOTTA_FRAME = new Color(0.84F, 0.54F, 0.37F, 1.0F);
    private static final Color TERRACOTTA_OUTLINE = new Color(0.16F, 0.08F, 0.05F, 1.0F);
    private static final Color TERRACOTTA_DESC = new Color(0.45F, 0.25F, 0.17F, 1.0F);
    private static final Color TERRACOTTA_GLOW = new Color(0.95F, 0.75F, 0.58F, 1.0F);
    private static final String BG_SHARED = CardEnergyMod.CARD_BACK_DIR + "/bg_skill.png";
    private static final String ENERGY_ORB = CardEnergyMod.CARD_BACK_DIR + "/energy_orb.png";
    private static final String BG_SHARED_P = CardEnergyMod.CARD_BACK_DIR + "/bg_skill_p.png";
    private static final String ENERGY_ORB_P = CardEnergyMod.CARD_BACK_DIR + "/energy_orb_p.png";
    private static final String SMALL_ORB = CardEnergyMod.CARD_BACK_DIR + "/small_orb.png";

    private TerracottaColorScheme() {
    }

    public static void register() {
        BaseMod.addColor(
                CardEnergyCharacterEnum.TERRACOTTA,
                TERRACOTTA_BG,
                TERRACOTTA_BACK,
                TERRACOTTA_FRAME,
                TERRACOTTA_OUTLINE,
                TERRACOTTA_DESC,
                TERRACOTTA,
                TERRACOTTA_GLOW,
                BG_SHARED,
                BG_SHARED,
                BG_SHARED,
                ENERGY_ORB,
                BG_SHARED_P,
                BG_SHARED_P,
                BG_SHARED_P,
                ENERGY_ORB_P,
                SMALL_ORB
        );
    }
}
