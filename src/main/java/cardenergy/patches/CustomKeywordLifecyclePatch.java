package cardenergy.patches;

import cardenergy.util.CardKeywordHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.actions.unique.RestoreRetainedCardsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class CustomKeywordLifecyclePatch {
    private CustomKeywordLifecyclePatch() {
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "triggerOnManualDiscard"
    )
    public static class ManualDiscard {
        @SpirePostfixPatch
        public static void Postfix(AbstractCard __instance) {
            CardKeywordHelper.handleManualDiscardKeywords(__instance);
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "triggerOnEndOfPlayerTurn"
    )
    public static class EndOfTurn {
        @SpirePostfixPatch
        public static void Postfix(AbstractCard __instance) {
            CardKeywordHelper.handleEndOfTurnKeywords(__instance);
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "onRetained"
    )
    public static class OnRetained {
        @SpirePostfixPatch
        public static void Postfix(AbstractCard __instance) {
            CardKeywordHelper.handleRetainedKeywords(__instance);
        }
    }

    @SpirePatch(
            clz = RestoreRetainedCardsAction.class,
            method = "update"
    )
    public static class RestoreRetained {
        @SpirePostfixPatch
        public static void Postfix(RestoreRetainedCardsAction __instance) {
            if (AbstractDungeon.player == null || AbstractDungeon.player.hand == null) {
                return;
            }

            for (AbstractCard card : AbstractDungeon.player.hand.group) {
                if (CardKeywordHelper.hasRot(card)) {
                    CardKeywordHelper.exhaustFromCurrentPile(card);
                }
            }

            AbstractDungeon.player.hand.applyPowers();
            AbstractDungeon.player.hand.glowCheck();
        }
    }
}
