package cardenergy.actions;

import cardenergy.util.HandFuelSelectionHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ThornsPower;

import java.util.ArrayList;

public class SelectHandExhaustForThornsAction extends AbstractGameAction {
    private enum Phase {
        OPENING_SELECTION,
        WAITING_FOR_SELECTION
    }

    private final AbstractPlayer player;
    private final int maxAmount;
    private final int thornsPerExhaust;
    private final String prompt;
    private final ArrayList<AbstractCard> originalHandOrder = new ArrayList<>();
    private Phase phase = Phase.OPENING_SELECTION;

    public SelectHandExhaustForThornsAction(AbstractPlayer player, int maxAmount, int thornsPerExhaust, String prompt) {
        this.player = player;
        this.maxAmount = Math.max(1, maxAmount);
        this.thornsPerExhaust = Math.max(0, thornsPerExhaust);
        this.prompt = prompt;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (phase == Phase.OPENING_SELECTION) {
            openSelection();
            return;
        }

        if (AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            isDone = true;
            return;
        }

        if (AbstractDungeon.isScreenUp) {
            return;
        }

        completeSelection();
        isDone = true;
    }

    private void openSelection() {
        if (player == null || player.hand == null || player.hand.isEmpty()) {
            isDone = true;
            return;
        }

        int available = player.hand.size();
        int amount = Math.min(maxAmount, available);

        HandFuelSelectionHelper.openHandSelectionForCards(
                player,
                player.hand.group,
                originalHandOrder,
                prompt,
                amount,
                true,
                true
        );
        phase = Phase.WAITING_FOR_SELECTION;
    }

    private void completeSelection() {
        ArrayList<AbstractCard> chosenCards = new ArrayList<>(AbstractDungeon.handCardSelectScreen.selectedCards.group);
        HandFuelSelectionHelper.clearHandSelectionState();
        HandFuelSelectionHelper.restoreHandOrder(player, originalHandOrder);

        if (chosenCards.isEmpty()) {
            return;
        }

        for (AbstractCard card : chosenCards) {
            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, player.hand));
        }

        int thorns = chosenCards.size() * thornsPerExhaust;
        if (thorns > 0) {
            AbstractDungeon.actionManager.addToBottom(
                    new ApplyPowerAction(player, player, new ThornsPower(player, thorns), thorns)
            );
        }

        player.hand.refreshHandLayout();
        player.hand.applyPowers();
    }
}
