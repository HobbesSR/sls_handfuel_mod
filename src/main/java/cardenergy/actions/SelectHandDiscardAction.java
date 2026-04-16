package cardenergy.actions;

import cardenergy.util.HandFuelSelectionHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class SelectHandDiscardAction extends AbstractGameAction {
    private enum Phase {
        OPENING_SELECTION,
        WAITING_FOR_SELECTION
    }

    private final AbstractPlayer player;
    private final String prompt;
    private final int amount;
    private final ArrayList<AbstractCard> originalHandOrder = new ArrayList<>();
    private Phase phase = Phase.OPENING_SELECTION;

    public SelectHandDiscardAction(AbstractPlayer player, String prompt) {
        this(player, prompt, 1);
    }

    public SelectHandDiscardAction(AbstractPlayer player, String prompt, int amount) {
        this.player = player;
        this.prompt = prompt;
        this.amount = Math.max(1, amount);
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

        if (player.hand.size() <= amount) {
            ArrayList<AbstractCard> cardsToDiscard = new ArrayList<>(player.hand.group);
            for (AbstractCard card : cardsToDiscard) {
                discardSelectedCard(card);
            }
            isDone = true;
            return;
        }

        HandFuelSelectionHelper.openHandSelectionForCards(
                player,
                player.hand.group,
                originalHandOrder,
                prompt,
                amount,
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

        int discardCount = Math.min(amount, chosenCards.size());
        for (int i = 0; i < discardCount; i++) {
            discardSelectedCard(chosenCards.get(i));
        }
        player.hand.refreshHandLayout();
        player.hand.applyPowers();
    }

    private void discardSelectedCard(AbstractCard card) {
        if (card == null || player == null || player.hand == null || !player.hand.group.contains(card)) {
            return;
        }
        card.triggerOnManualDiscard();
        player.hand.moveToDiscardPile(card);
        GameActionManager.incrementDiscard(false);
    }
}
