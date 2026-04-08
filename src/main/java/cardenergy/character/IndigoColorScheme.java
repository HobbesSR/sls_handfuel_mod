package cardenergy.character;

import basemod.BaseMod;
import cardenergy.CardEnergyMod;
import com.badlogic.gdx.graphics.Color;

public final class IndigoColorScheme {
    public static final Color INDIGO = new Color(0.33F, 0.27F, 0.68F, 1.0F);
    private static final Color INDIGO_BG = new Color(0.17F, 0.14F, 0.31F, 1.0F);
    private static final Color INDIGO_BACK = new Color(0.28F, 0.23F, 0.55F, 1.0F);
    private static final Color INDIGO_FRAME = new Color(0.45F, 0.38F, 0.82F, 1.0F);
    private static final Color INDIGO_OUTLINE = new Color(0.09F, 0.08F, 0.18F, 1.0F);
    private static final Color INDIGO_DESC = new Color(0.20F, 0.18F, 0.36F, 1.0F);
    private static final Color INDIGO_GLOW = new Color(0.73F, 0.69F, 0.96F, 1.0F);
    private static final String BG_SHARED = CardEnergyMod.CARD_BACK_DIR + "/bg_skill.png";
    private static final String ENERGY_ORB = CardEnergyMod.CARD_BACK_DIR + "/energy_orb.png";
    private static final String BG_SHARED_P = CardEnergyMod.CARD_BACK_DIR + "/bg_skill_p.png";
    private static final String ENERGY_ORB_P = CardEnergyMod.CARD_BACK_DIR + "/energy_orb_p.png";
    private static final String SMALL_ORB = CardEnergyMod.CARD_BACK_DIR + "/small_orb.png";

    private IndigoColorScheme() {
    }

    public static void register() {
        BaseMod.addColor(
                CardEnergyCharacterEnum.INDIGO,
                INDIGO_BG,
                INDIGO_BACK,
                INDIGO_FRAME,
                INDIGO_OUTLINE,
                INDIGO_DESC,
                INDIGO,
                INDIGO_GLOW,
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
