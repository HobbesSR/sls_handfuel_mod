package cardenergy.patches.combat;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class SalvagerCombatStateFieldPatch {
    private SalvagerCombatStateFieldPatch() {
    }

    @SpirePatch(clz = AbstractPlayer.class, method = SpirePatch.CLASS)
    public static class Fields {
        public static final SpireField<Integer> exhaustedThisTurn = new SpireField<>(() -> 0);
        public static final SpireField<Boolean> wasAttackedLastTurn = new SpireField<>(() -> false);
        public static final SpireField<Boolean> wasAttackedSinceLastTurn = new SpireField<>(() -> false);
        public static final SpireField<Integer> pendingBarbedGuard = new SpireField<>(() -> 0);
        public static final SpireField<Integer> pendingTurnAside = new SpireField<>(() -> 0);
        public static final SpireField<Boolean> turnAsideSafe = new SpireField<>(() -> true);
    }
}
