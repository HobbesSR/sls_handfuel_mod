package cardenergy.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;

import java.util.ArrayList;

public class DiscardEntireHandAction extends AbstractGameAction {
    private final AbstractPlayer player;

    public DiscardEntireHandAction(AbstractPlayer player) {
        this.player = player;
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (player != null && player.hand != null && !player.hand.isEmpty()) {
            ArrayList<AbstractCard> cards = new ArrayList<>(player.hand.group);
            for (AbstractCard card : cards) {
                if (!player.hand.group.contains(card)) {
                    continue;
                }
                card.triggerOnManualDiscard();
                player.hand.moveToDiscardPile(card);
                GameActionManager.incrementDiscard(false);
            }
            player.hand.refreshHandLayout();
            player.hand.applyPowers();
        }
        isDone = true;
    }
}
