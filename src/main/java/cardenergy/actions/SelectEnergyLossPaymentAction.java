package cardenergy.actions;

import cardenergy.util.HandFuelResourceAdapter;
import cardenergy.util.HandFuelPaymentHelper;
import cardenergy.util.HandFuelSelectionHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.List;

public class SelectEnergyLossPaymentAction extends AbstractGameAction {
    private enum Phase {
        OPENING_SELECTION,
        WAITING_FOR_SELECTION
    }

    private static final String PROMPT = "Choose cards to discard for lost energy.";

    private final AbstractPlayer player;
    private final int discardAmount;
    private final ArrayList<AbstractCard> originalHandOrder = new ArrayList<>();
    private Phase phase = Phase.OPENING_SELECTION;

    public SelectEnergyLossPaymentAction(AbstractPlayer player, int discardAmount) {
        this.player = player;
        this.discardAmount = discardAmount;
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
        List<AbstractCard> discardCards = HandFuelResourceAdapter.getEnergyLossDiscardCardsInHand(player);
        if (discardCards.isEmpty()) {
            isDone = true;
            return;
        }
        HandFuelSelectionHelper.openHandSelectionForCards(
                player,
                discardCards,
                originalHandOrder,
                PROMPT,
                discardAmount,
                false,
                false
        );
        phase = Phase.WAITING_FOR_SELECTION;
    }

    private void completeSelection() {
        ArrayList<AbstractCard> chosenCards = new ArrayList<>(AbstractDungeon.handCardSelectScreen.selectedCards.group);
        HandFuelSelectionHelper.clearHandSelectionState();
        HandFuelSelectionHelper.restoreHandOrder(player, originalHandOrder);
        HandFuelPaymentHelper.spendFuelCards(player, chosenCards);
    }
}
