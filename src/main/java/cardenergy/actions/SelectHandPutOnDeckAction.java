package cardenergy.actions;

import cardenergy.util.HandFuelSelectionHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class SelectHandPutOnDeckAction extends AbstractGameAction {
    private enum Phase {
        OPENING_SELECTION,
        WAITING_FOR_SELECTION
    }

    private final AbstractPlayer player;
    private final String prompt;
    private final ArrayList<AbstractCard> originalHandOrder = new ArrayList<>();
    private Phase phase = Phase.OPENING_SELECTION;

    public SelectHandPutOnDeckAction(AbstractPlayer player, String prompt) {
        this.player = player;
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

        if (player.hand.size() == 1) {
            moveSelectedCard(player.hand.getTopCard());
            isDone = true;
            return;
        }

        HandFuelSelectionHelper.openHandSelectionForCards(
                player,
                player.hand.group,
                originalHandOrder,
                prompt,
                1,
                false,
                false
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

        moveSelectedCard(chosenCards.get(0));
        player.hand.refreshHandLayout();
        player.hand.applyPowers();
    }

    private void moveSelectedCard(AbstractCard card) {
        if (card == null || player == null || player.hand == null || !player.hand.group.contains(card)) {
            return;
        }
        player.hand.moveToDeck(card, false);
    }
}
