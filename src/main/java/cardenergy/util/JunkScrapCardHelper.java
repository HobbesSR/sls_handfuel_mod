package cardenergy.util;

import cardenergy.cards.status.Junk;
import cardenergy.cards.status.Scrap;
import cardenergy.character.CardEnergyCharacterEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;

import java.util.ArrayList;

public final class JunkScrapCardHelper {
    private JunkScrapCardHelper() {
    }

    public static boolean isJunk(AbstractCard card) {
        return card != null && Junk.ID.equals(card.cardID);
    }

    public static boolean isScrap(AbstractCard card) {
        return card != null && Scrap.ID.equals(card.cardID);
    }

    public static boolean isJunkOrScrap(AbstractCard card) {
        return isJunk(card) || isScrap(card);
    }

    public static int countJunkOrScrapInHand(AbstractPlayer player) {
        if (player == null || player.hand == null) {
            return 0;
        }

        int count = 0;
        for (AbstractCard card : player.hand.group) {
            if (isJunkOrScrap(card)) {
                count++;
            }
        }
        return count;
    }

    public static int countScrapInHand(AbstractPlayer player) {
        if (player == null || player.hand == null) {
            return 0;
        }

        int count = 0;
        for (AbstractCard card : player.hand.group) {
            if (isScrap(card)) {
                count++;
            }
        }
        return count;
    }

    public static AbstractCard getRandomTerracottaByRarity(AbstractCard.CardRarity rarity) {
        ArrayList<AbstractCard> candidates = new ArrayList<>();
        for (AbstractCard card : CardLibrary.getAllCards()) {
            if (card == null) {
                continue;
            }
            if (card.color != CardEnergyCharacterEnum.TERRACOTTA) {
                continue;
            }
            if (card.rarity != rarity) {
                continue;
            }
            if (card.type == AbstractCard.CardType.CURSE || card.type == AbstractCard.CardType.STATUS) {
                continue;
            }
            candidates.add(card);
        }

        if (candidates.isEmpty()) {
            return null;
        }

        int idx = AbstractDungeon.cardRandomRng.random(candidates.size() - 1);
        return candidates.get(idx).makeCopy();
    }
}
