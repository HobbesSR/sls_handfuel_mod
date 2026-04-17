package cardenergy.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class SelectDiscardToExhaustAction extends AbstractGameAction {
    private enum Phase {
        OPENING_SELECTION,
        WAITING_FOR_SELECTION
    }

    private final AbstractPlayer player;
    private final int amount;
    private final String prompt;
    private final CardGroup selectionGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
    private Phase phase = Phase.OPENING_SELECTION;

    public SelectDiscardToExhaustAction(AbstractPlayer player, int amount, String prompt) {
        this.player = player;
        this.amount = amount;
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

        if (AbstractDungeon.isScreenUp) {
            return;
        }

        completeSelection();
        isDone = true;
    }

    private void openSelection() {
        for (AbstractCard card : player.discardPile.group) {
            selectionGroup.addToTop(card);
        }

        if (selectionGroup.isEmpty()) {
            isDone = true;
            return;
        }

        AbstractDungeon.gridSelectScreen.open(selectionGroup, amount, true, prompt);
        phase = Phase.WAITING_FOR_SELECTION;
    }

    private void completeSelection() {
        for (AbstractCard card : new ArrayList<>(AbstractDungeon.gridSelectScreen.selectedCards)) {
            card.unhover();
            card.lighten(false);
            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, player.discardPile));
        }
        AbstractDungeon.gridSelectScreen.selectedCards.clear();
    }
}
