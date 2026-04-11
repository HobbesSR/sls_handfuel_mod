package cardenergy.util;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.List;

public final class HandFuelSelectionHelper {
    private HandFuelSelectionHelper() {
    }

    public static void openHandSelectionForCards(
            AbstractPlayer player,
            List<AbstractCard> availableCards,
            List<AbstractCard> originalHandOrder,
            String prompt,
            int amount,
            boolean anyNumber,
            boolean canPickZero
    ) {
        originalHandOrder.clear();
        originalHandOrder.addAll(player.hand.group);

        ArrayList<AbstractCard> hiddenCards = new ArrayList<>();
        for (AbstractCard handCard : originalHandOrder) {
            if (!availableCards.contains(handCard)) {
                hiddenCards.add(handCard);
            }
        }
        for (AbstractCard hiddenCard : hiddenCards) {
            player.hand.removeCard(hiddenCard);
        }
        for (AbstractCard selectableCard : player.hand.group) {
            selectableCard.unhover();
            selectableCard.isSelected = false;
            selectableCard.stopGlowing();
        }
        player.hand.refreshHandLayout();
        player.hand.glowCheck();
        player.hand.applyPowers();

        AbstractDungeon.handCardSelectScreen.open(prompt, amount, anyNumber, canPickZero);
    }

    public static void clearHandSelectionState() {
        if (AbstractDungeon.handCardSelectScreen == null) {
            return;
        }
        AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
        AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
    }

    public static void restoreHandOrder(AbstractPlayer player, List<AbstractCard> originalHandOrder) {
        if (player == null || player.hand == null) {
            return;
        }
        player.hand.group.clear();
        for (AbstractCard handCard : originalHandOrder) {
            player.hand.addToBottom(handCard);
        }
        player.hand.refreshHandLayout();
        player.hand.applyPowers();
    }
}
