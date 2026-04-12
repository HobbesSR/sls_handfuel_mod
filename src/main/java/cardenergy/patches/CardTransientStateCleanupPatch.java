package cardenergy.patches;

import cardenergy.util.CardKeywordHelper;
import cardenergy.util.HandFuelResourceAdapter;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class CardTransientStateCleanupPatch {
    private CardTransientStateCleanupPatch() {
    }

    private static void clearTransientState(AbstractCard card) {
        HandFuelResourceAdapter.clearPrepaidReplay(card);
        CardKeywordHelper.clearPendingDiscardReplacement(card);
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "triggerWhenDrawn"
    )
    public static class ClearOnDraw {
        @SpirePostfixPatch
        public static void Postfix(AbstractCard __instance) {
            clearTransientState(__instance);
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "onMoveToDiscard"
    )
    public static class ClearOnMoveToDiscard {
        @SpirePostfixPatch
        public static void Postfix(AbstractCard __instance) {
            clearTransientState(__instance);
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "triggerOnExhaust"
    )
    public static class ClearOnExhaust {
        @SpirePostfixPatch
        public static void Postfix(AbstractCard __instance) {
            clearTransientState(__instance);
        }
    }
}
