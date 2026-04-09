package cardenergy.util;

import cardenergy.CardEnergyMod;
import cardenergy.actions.SelectFuelPaymentAction;
import cardenergy.character.CardEnergyCharacterEnum;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

public class HandFuelResourceAdapter {
    public static final int FUEL_SURCHARGE = 1;
    private static final Set<AbstractCard> replayingCards =
            Collections.newSetFromMap(new IdentityHashMap<AbstractCard, Boolean>());
    private static final Set<AbstractCard> suppressEnergySpendCards =
            Collections.newSetFromMap(new IdentityHashMap<AbstractCard, Boolean>());

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
        if (isReplayingCard(card)) {
            replayingCards.remove(card);
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

    public static void markCardForReplay(AbstractCard card) {
        replayingCards.add(card);
        suppressEnergySpendCards.add(card);
    }

    public static boolean isReplayingCard(AbstractCard card) {
        return replayingCards.contains(card);
    }

    public static boolean shouldSuppressEnergySpend(AbstractPlayer player, AbstractCard card) {
        if (!isActive(player) || card == null) {
            return false;
        }
        if (!suppressEnergySpendCards.contains(card)) {
            return false;
        }
        suppressEnergySpendCards.remove(card);
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
            return FUEL_SURCHARGE;
        }
        return Math.max(0, card.costForTurn) + FUEL_SURCHARGE;
    }

    public static int getXValueFromSelectedFuel(int selectedFuelCount) {
        return Math.max(0, selectedFuelCount - FUEL_SURCHARGE);
    }

    public static String getDisplayedCost(AbstractCard card) {
        if (card == null) {
            return null;
        }
        if (card.freeToPlayOnce) {
            return "0";
        }
        if (card.costForTurn == -2) {
            return "";
        }
        if (card.costForTurn == -1) {
            return "X+" + FUEL_SURCHARGE;
        }
        return Integer.toString(getRequiredFuel(card));
    }
}
