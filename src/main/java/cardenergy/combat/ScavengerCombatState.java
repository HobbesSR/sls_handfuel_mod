package cardenergy.combat;

import cardenergy.patches.combat.ScavengerCombatStateFieldPatch;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public final class ScavengerCombatState {
    private static final int BARBED_GUARD_DAMAGE = 4;

    private ScavengerCombatState() {
    }

    public static void resetForCombat(AbstractPlayer player) {
        if (player == null) {
            return;
        }
        setExhaustedThisTurn(player, 0);
        setWasAttackedLastTurn(player, false);
        setWasAttackedSinceLastTurn(player, false);
        setPendingBarbedGuard(player, 0);
        setPendingTurnAside(player, 0);
        setTurnAsideSafe(player, true);
    }

    public static void beginPlayerTurn(AbstractPlayer player) {
        if (player == null) {
            return;
        }
        int pendingTurnAside = getPendingTurnAside(player);
        if (pendingTurnAside > 0 && isTurnAsideSafe(player)) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, pendingTurnAside));
        }

        setWasAttackedLastTurn(player, wasAttackedSinceLastTurn(player));
        setWasAttackedSinceLastTurn(player, false);
        setPendingBarbedGuard(player, 0);
        setPendingTurnAside(player, 0);
        setTurnAsideSafe(player, true);
        setExhaustedThisTurn(player, 0);
    }

    public static void onCardExhausted(AbstractPlayer player) {
        if (player != null) {
            setExhaustedThisTurn(player, getExhaustedThisTurn(player) + 1);
        }
    }

    public static int getExhaustedThisTurn(AbstractPlayer player) {
        return ScavengerCombatStateFieldPatch.Fields.exhaustedThisTurn.get(player);
    }

    public static boolean wasAttackedLastTurn(AbstractPlayer player) {
        return ScavengerCombatStateFieldPatch.Fields.wasAttackedLastTurn.get(player);
    }

    public static void addPendingBarbedGuard(AbstractPlayer player, int amount) {
        if (player != null && amount > 0) {
            setPendingBarbedGuard(player, getPendingBarbedGuard(player) + amount);
        }
    }

    public static void addPendingTurnAside(AbstractPlayer player, int amount) {
        if (player != null && amount > 0) {
            setPendingTurnAside(player, getPendingTurnAside(player) + amount);
            setTurnAsideSafe(player, true);
        }
    }

    public static void onPlayerAttacked(AbstractPlayer player, DamageInfo info, int hpLoss) {
        if (player == null || !(info.owner instanceof AbstractMonster)) {
            return;
        }

        setWasAttackedSinceLastTurn(player, true);
        if (hpLoss > 0) {
            setTurnAsideSafe(player, false);
        }

        int pendingBarbedGuard = getPendingBarbedGuard(player);
        if (pendingBarbedGuard > 0) {
            AbstractMonster attacker = (AbstractMonster) info.owner;
            for (int i = 0; i < pendingBarbedGuard; i++) {
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(attacker, new DamageInfo(player, BARBED_GUARD_DAMAGE, DamageInfo.DamageType.THORNS))
                );
            }
            setPendingBarbedGuard(player, 0);
        }
    }

    private static int getPendingBarbedGuard(AbstractPlayer player) {
        return ScavengerCombatStateFieldPatch.Fields.pendingBarbedGuard.get(player);
    }

    private static void setPendingBarbedGuard(AbstractPlayer player, int amount) {
        ScavengerCombatStateFieldPatch.Fields.pendingBarbedGuard.set(player, amount);
    }

    private static int getPendingTurnAside(AbstractPlayer player) {
        return ScavengerCombatStateFieldPatch.Fields.pendingTurnAside.get(player);
    }

    private static void setPendingTurnAside(AbstractPlayer player, int amount) {
        ScavengerCombatStateFieldPatch.Fields.pendingTurnAside.set(player, amount);
    }

    private static boolean isTurnAsideSafe(AbstractPlayer player) {
        return ScavengerCombatStateFieldPatch.Fields.turnAsideSafe.get(player);
    }

    private static void setTurnAsideSafe(AbstractPlayer player, boolean value) {
        ScavengerCombatStateFieldPatch.Fields.turnAsideSafe.set(player, value);
    }

    private static boolean wasAttackedSinceLastTurn(AbstractPlayer player) {
        return ScavengerCombatStateFieldPatch.Fields.wasAttackedSinceLastTurn.get(player);
    }

    private static void setWasAttackedSinceLastTurn(AbstractPlayer player, boolean value) {
        ScavengerCombatStateFieldPatch.Fields.wasAttackedSinceLastTurn.set(player, value);
    }

    private static void setWasAttackedLastTurn(AbstractPlayer player, boolean value) {
        ScavengerCombatStateFieldPatch.Fields.wasAttackedLastTurn.set(player, value);
    }

    private static void setExhaustedThisTurn(AbstractPlayer player, int amount) {
        ScavengerCombatStateFieldPatch.Fields.exhaustedThisTurn.set(player, amount);
    }
}
