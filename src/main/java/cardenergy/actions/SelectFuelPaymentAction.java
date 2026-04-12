package cardenergy.actions;

import cardenergy.util.HandFuelResourceAdapter;
import cardenergy.util.HandFuelPaymentHelper;
import cardenergy.util.HandFuelPaymentPlan;
import cardenergy.util.HandFuelSelectionHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.List;

public class SelectFuelPaymentAction extends AbstractGameAction {
    private enum Phase {
        OPENING_SELECTION,
        WAITING_FOR_SELECTION
    }

    private static final String PROMPT_TEMPLATE =
            "discard.\nSelect %d cards to finish playing your card.\nSelect 0 to cancel playing your card.";

    private final AbstractPlayer player;
    private final AbstractCard card;
    private final AbstractMonster monster;
    private final int energyOnUse;
    private final boolean isXCost;
    private final int requiredFuel;
    private final ArrayList<AbstractCard> originalHandOrder = new ArrayList<>();
    private Phase phase = Phase.OPENING_SELECTION;

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
        if (isXCost) {
            autoPayForX();
            isDone = true;
            return;
        }

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

        isDone = completeSelection();
    }

    private void openSelection() {
        List<AbstractCard> fuelCards = HandFuelResourceAdapter.getFuelCardsInHand(player, card);
        if (fuelCards.isEmpty()) {
            isDone = true;
            return;
        }
        HandFuelSelectionHelper.openHandSelectionForCards(
                player,
                fuelCards,
                originalHandOrder,
                getPromptText(),
                requiredFuel,
                true,
                true
        );
        phase = Phase.WAITING_FOR_SELECTION;
    }

    private boolean completeSelection() {
        ArrayList<AbstractCard> chosenFuel = new ArrayList<>(AbstractDungeon.handCardSelectScreen.selectedCards.group);
        HandFuelSelectionHelper.clearHandSelectionState();
        HandFuelSelectionHelper.restoreHandOrder(player, originalHandOrder);

        if (chosenFuel.isEmpty()) {
            return true;
        }

        if (!isXCost && chosenFuel.size() != requiredFuel) {
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = false;
            phase = Phase.OPENING_SELECTION;
            return false;
        }

        HandFuelPaymentHelper.executePaymentPlan(
                player,
                card,
                monster,
                HandFuelPaymentPlan.forFixedCost(chosenFuel, energyOnUse)
        );
        return true;
    }

    private void autoPayForX() {
        HandFuelPaymentPlan paymentPlan = HandFuelResourceAdapter.buildXPaymentPlan(player, card);
        HandFuelPaymentHelper.executePaymentPlan(player, card, monster, paymentPlan);
    }

    private String getPromptText() {
        return String.format(PROMPT_TEMPLATE, requiredFuel, requiredFuel == 1 ? "" : "s");
    }
}
