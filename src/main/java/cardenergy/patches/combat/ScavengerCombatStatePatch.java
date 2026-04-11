package cardenergy.patches.combat;

import cardenergy.combat.ScavengerCombatState;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class ScavengerCombatStatePatch {
    private ScavengerCombatStatePatch() {
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "preBattlePrep")
    public static class ResetOnBattleStart {
        @SpirePostfixPatch
        public static void Postfix(AbstractPlayer __instance) {
            ScavengerCombatState.resetForCombat(__instance);
        }
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "applyStartOfTurnCards")
    public static class BeginTurn {
        @SpirePostfixPatch
        public static void Postfix(AbstractPlayer __instance) {
            ScavengerCombatState.beginPlayerTurn(__instance);
        }
    }

    @SpirePatch(clz = AbstractPlayer.class, method = "damage")
    public static class TrackIncomingAttacks {
        @SpirePrefixPatch
        public static void Prefix(AbstractPlayer __instance) {
            DamageContext.hpBeforeDamage = __instance.currentHealth;
        }

        @SpirePostfixPatch
        public static void Postfix(AbstractPlayer __instance, DamageInfo info) {
            int hpLoss = Math.max(0, DamageContext.hpBeforeDamage - __instance.currentHealth);
            ScavengerCombatState.onPlayerAttacked(__instance, info, hpLoss);
        }
    }

    @SpirePatch(clz = AbstractCard.class, method = "triggerOnExhaust")
    public static class TrackExhausts {
        @SpirePostfixPatch
        public static void Postfix(AbstractCard __instance) {
            ScavengerCombatState.onCardExhausted(com.megacrit.cardcrawl.dungeons.AbstractDungeon.player);
        }
    }

    private static class DamageContext {
        private static int hpBeforeDamage;
    }
}
