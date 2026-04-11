package cardenergy.util;

import cardenergy.CardEnergyMod;
import cardenergy.actions.SelectEnergyLossPaymentAction;
import cardenergy.actions.SelectFuelPaymentAction;
import cardenergy.character.CardEnergyCharacterEnum;
import cardenergy.patches.PrepaidReplayFieldPatch;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class HandFuelResourceAdapter {
    public static final int NON_X_SURCHARGE = 1;

    private HandFuelResourceAdapter() {
    }

    public static boolean isActive(AbstractPlayer player) {
        return player != null && player.chosenClass == CardEnergyCharacterEnum.HAND_FUEL_INDIGO;
    }

    public static boolean isActivePlayer() {
        return AbstractDungeon.player != null && isActive(AbstractDungeon.player);
    }

    public static boolean isFuelCard(AbstractPlayer player, AbstractCard card, AbstractCard cardBeingPlayed) {
        return player != null
                && card != null
                && card != cardBeingPlayed
                && card.color == player.getCardColor();
    }

    public static boolean usesFuelPaymentModel(AbstractCard card) {
        return card != null && card.cost != -2 && card.costForTurn != -2;
    }

    public static List<AbstractCard> getFuelCardsInHand(AbstractPlayer player, AbstractCard cardBeingPlayed) {
        ArrayList<AbstractCard> fuel = new ArrayList<>();
        if (player == null || player.hand == null) {
            return fuel;
        }

        for (AbstractCard card : player.hand.group) {
            if (isFuelCard(player, card, cardBeingPlayed)) {
                fuel.add(card);
            }
        }
        return fuel;
    }

    public static int getFuelCount(AbstractPlayer player, AbstractCard cardBeingPlayed) {
        return getFuelCardsInHand(player, cardBeingPlayed).size();
    }

    public static boolean canPay(AbstractPlayer player, AbstractCard card) {
        if (!isActive(player) || card == null) {
            return false;
        }
        if (!usesFuelPaymentModel(card)) {
            return false;
        }
        if (player.hand == null || !player.hand.group.contains(card)) {
            return card.freeToPlayOnce || card.costForTurn == 0;
        }
        if (card.freeToPlayOnce) {
            return true;
        }
        return getFuelCount(player, card) >= getRequiredFuel(card);
    }

    public static boolean shouldInterceptCardUse(AbstractPlayer player, AbstractCard card) {
        if (!isActive(player) || card == null) {
            return false;
        }
        if (!usesFuelPaymentModel(card)) {
            return false;
        }
        if (isPrepaidReplay(card)) {
            return false;
        }
        if (player.hand == null || !player.hand.group.contains(card)) {
            return false;
        }
        if (card.freeToPlayOnce) {
            return false;
        }
        return getFuelCount(player, card) >= getRequiredFuel(card);
    }

    public static void queueFuelSelection(AbstractPlayer player, AbstractCard card, AbstractMonster monster, int energyOnUse) {
        AbstractDungeon.actionManager.addToTop(new SelectFuelPaymentAction(player, card, monster, energyOnUse));
        CardEnergyMod.logger.info("Queued fuel selection for {}", card.cardID);
    }

    public static boolean shouldTreatAsPlayable(AbstractCard card) {
        return isActivePlayer() && canPay(AbstractDungeon.player, card);
    }

    public static void markCardAsPrepaidReplay(AbstractCard card) {
        if (card != null) {
            PrepaidReplayFieldPatch.prepaidReplay.set(card, true);
        }
    }

    public static void clearPrepaidReplay(AbstractCard card) {
        if (card != null) {
            PrepaidReplayFieldPatch.prepaidReplay.set(card, false);
        }
    }

    public static boolean isPrepaidReplay(AbstractCard card) {
        return card != null && PrepaidReplayFieldPatch.prepaidReplay.get(card);
    }

    public static boolean shouldSuppressEnergySpend(AbstractPlayer player, AbstractCard card) {
        if (!isActive(player) || card == null) {
            return false;
        }
        if (!PrepaidReplayFieldPatch.prepaidReplay.get(card)) {
            return false;
        }
        clearPrepaidReplay(card);
        return true;
    }

    public static void replaceEnergyGainWithDraw(AbstractPlayer player, int amount, String source) {
        if (!isActive(player) || amount <= 0) {
            return;
        }
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(player, amount));
        CardEnergyMod.logger.info("Converted {} energy from {} into draw", amount, source);
    }

    public static int getRechargeAmount(EnergyManager energyManager) {
        try {
            Field field = EnergyManager.class.getDeclaredField("energyMaster");
            field.setAccessible(true);
            return field.getInt(energyManager);
        } catch (ReflectiveOperationException e) {
            CardEnergyMod.logger.error("Could not read energyMaster from EnergyManager", e);
            return 3;
        }
    }

    public static int getRequiredFuel(AbstractCard card) {
        if (card == null || card.freeToPlayOnce) {
            return 0;
        }
        if (card.costForTurn == -1) {
            return 0;
        }
        return Math.max(0, card.costForTurn) + NON_X_SURCHARGE;
    }

    public static int getXValueFromSelectedFuel(int selectedFuelCount) {
        return (selectedFuelCount + 1) / 2;
    }

    public static HandFuelPaymentPlan buildXPaymentPlan(AbstractPlayer player, AbstractCard card) {
        List<AbstractCard> fuelCards = getFuelCardsInHand(player, card);
        return HandFuelPaymentPlan.forXCost(fuelCards, getXValueFromSelectedFuel(fuelCards.size()));
    }

    public static List<AbstractCard> getEnergyLossDiscardCardsInHand(AbstractPlayer player) {
        return getFuelCardsInHand(player, null);
    }

    public static void replaceEnergyLossWithDiscard(AbstractPlayer player, int amount) {
        if (!isActive(player) || amount <= 0) {
            return;
        }
        List<AbstractCard> cards = getEnergyLossDiscardCardsInHand(player);
        if (cards.isEmpty()) {
            return;
        }
        int discardAmount = Math.min(amount, cards.size());
        if (cards.size() <= discardAmount) {
            HandFuelPaymentHelper.spendFuelCards(player, cards);
            CardEnergyMod.logger.info("Converted loseEnergy({}) into discarding all {} available fuel cards", amount, cards.size());
            return;
        }
        AbstractDungeon.actionManager.addToTop(new SelectEnergyLossPaymentAction(player, discardAmount));
        CardEnergyMod.logger.info("Converted loseEnergy({}) into selecting {} fuel cards to discard", amount, discardAmount);
    }

    public static String getDisplayedCost(AbstractCard card) {
        if (card == null) {
            return null;
        }
        if (!usesFuelPaymentModel(card)) {
            return null;
        }
        if (card.freeToPlayOnce) {
            return "0";
        }
        if (card.costForTurn == -2) {
            return "";
        }
        if (card.costForTurn == -1) {
            return "X";
        }
        return Integer.toString(getRequiredFuel(card));
    }
}
