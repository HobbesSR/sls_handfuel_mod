package cardenergy.actions;

import cardenergy.util.HandFuelResourceAdapter;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

public class SelectFuelPaymentAction extends AbstractGameAction {
    private static final String NORMAL_PROMPT = "Choose fuel cards to discard, including the 1 fuel tax.";
    private static final String X_COST_PROMPT = "Choose fuel cards for X, including the 1 fuel tax.";

    private final AbstractPlayer player;
    private final AbstractCard card;
    private final AbstractMonster monster;
    private final int energyOnUse;
    private final boolean isXCost;
    private final int requiredFuel;
    private final ArrayList<AbstractCard> originalHandOrder = new ArrayList<>();
    private boolean openedSelection;

    public SelectFuelPaymentAction(AbstractPlayer player, AbstractCard card, AbstractMonster monster, int energyOnUse) {
        this.player = player;
        this.card = card;
        this.monster = monster;
        this.energyOnUse = energyOnUse;
        this.isXCost = card.costForTurn == -1;
        this.requiredFuel = HandFuelResourceAdapter.getRequiredFuel(card);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.duration = Settings.ACTION_DUR_FAST;
    }

    @Override
    public void update() {
        if (!openedSelection) {
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
        List<AbstractCard> fuelCards = HandFuelResourceAdapter.getFuelCardsInHand(player, card);
        if (fuelCards.isEmpty()) {
            replayPaidCard(0);
            isDone = true;
            return;
        }

        originalHandOrder.addAll(player.hand.group);
        ArrayList<AbstractCard> hiddenCards = new ArrayList<>();
        for (AbstractCard handCard : originalHandOrder) {
            if (!fuelCards.contains(handCard)) {
                hiddenCards.add(handCard);
            }
        }
        for (AbstractCard hiddenCard : hiddenCards) {
            player.hand.removeCard(hiddenCard);
        }
        for (AbstractCard fuelCard : player.hand.group) {
            fuelCard.unhover();
            fuelCard.isSelected = false;
            fuelCard.stopGlowing();
        }
        player.hand.refreshHandLayout();
        player.hand.glowCheck();
        player.hand.applyPowers();

        if (isXCost) {
            AbstractDungeon.handCardSelectScreen.open(X_COST_PROMPT, fuelCards.size(), true, true);
        } else {
            AbstractDungeon.handCardSelectScreen.open(NORMAL_PROMPT, requiredFuel, false, false);
        }
        openedSelection = true;
    }

    private void completeSelection() {
        ArrayList<AbstractCard> chosenFuel = new ArrayList<>(AbstractDungeon.handCardSelectScreen.selectedCards.group);

        for (AbstractCard selectedCard : chosenFuel) {
            selectedCard.triggerOnManualDiscard();
            if (AbstractDungeon.handCardSelectScreen.selectedCards.group.contains(selectedCard)) {
                AbstractDungeon.handCardSelectScreen.selectedCards.moveToDiscardPile(selectedCard);
            }
            GameActionManager.incrementDiscard(false);
        }
        AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
        AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;

        player.hand.group.clear();
        for (AbstractCard handCard : originalHandOrder) {
            if (!chosenFuel.contains(handCard)) {
                player.hand.addToBottom(handCard);
            }
        }
        player.hand.refreshHandLayout();
        player.hand.applyPowers();

        replayPaidCard(chosenFuel.size());
    }

    private void replayPaidCard(int selectedFuelCount) {
        if (isXCost) {
            card.energyOnUse = HandFuelResourceAdapter.getXValueFromSelectedFuel(selectedFuelCount);
        } else {
            card.energyOnUse = energyOnUse;
        }
        HandFuelResourceAdapter.markCardForReplay(card);
        player.useCard(card, monster, card.energyOnUse);
    }
}
