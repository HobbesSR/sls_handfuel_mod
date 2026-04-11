package cardenergy.util;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

public final class HandFuelPaymentHelper {
    private HandFuelPaymentHelper() {
    }

    public static void spendFuelCards(AbstractPlayer player, List<AbstractCard> chosenFuel) {
        if (player == null || player.hand == null || chosenFuel == null || chosenFuel.isEmpty()) {
            return;
        }
        for (AbstractCard selectedCard : new ArrayList<>(chosenFuel)) {
            selectedCard.triggerOnManualDiscard();
            if (discardFuelCardFromHand(player, selectedCard)) {
                GameActionManager.incrementDiscard(false);
            }
        }
        player.hand.refreshHandLayout();
        player.hand.applyPowers();
    }

    public static void replayPaidCard(AbstractPlayer player, AbstractCard card, AbstractMonster monster, int energyOnUse) {
        if (card.costForTurn == -1) {
            card.energyOnUse = energyOnUse;
            card.ignoreEnergyOnUse = true;
        } else {
            card.energyOnUse = energyOnUse;
        }
        AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
            @Override
            public void update() {
                HandFuelResourceAdapter.markCardAsPrepaidReplay(card);
                player.useCard(card, monster, card.energyOnUse);
                HandFuelResourceAdapter.clearPrepaidReplay(card);
                card.ignoreEnergyOnUse = false;
                isDone = true;
            }
        });
    }

    public static void executePaymentPlan(
            AbstractPlayer player,
            AbstractCard card,
            AbstractMonster monster,
            HandFuelPaymentPlan paymentPlan
    ) {
        spendFuelCards(player, paymentPlan.getFuelCards());
        replayPaidCard(player, card, monster, paymentPlan.getResolvedEnergyOnUse());
    }

    private static boolean discardFuelCardFromHand(AbstractPlayer player, AbstractCard selectedCard) {
        if (selectedCard == null || !player.hand.group.contains(selectedCard)) {
            return false;
        }
        player.hand.moveToDiscardPile(selectedCard);
        return true;
    }
}
