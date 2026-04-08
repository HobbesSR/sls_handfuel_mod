package cardenergy.cards;

import cardenergy.character.CardEnergyCharacterEnum;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;

public final class IndigoCardHelper {
    private static final Set<AbstractCard> skipDiscardCards =
            Collections.newSetFromMap(new IdentityHashMap<AbstractCard, Boolean>());

    private IndigoCardHelper() {
    }

    public static void applyIdentity(AbstractCard card, String id) {
        CardStrings strings = CardCrawlGame.languagePack.getCardStrings(id);
        card.cardID = id;
        card.name = strings.NAME;
        card.originalName = strings.NAME;
        card.rawDescription = strings.DESCRIPTION;
        card.color = CardEnergyCharacterEnum.INDIGO;
        card.initializeDescription();
    }

    public static void applyIdentityKeepBaseStrings(AbstractCard card, String id) {
        card.cardID = id;
        card.color = CardEnergyCharacterEnum.INDIGO;
        card.initializeDescription();
    }

    public static void addKeyword(AbstractCard card, String keyword) {
        card.keywords.add(keyword.toLowerCase());
        card.initializeDescription();
    }

    public static void triggerConsume(AbstractCard card) {
        AbstractCard copy = card.makeStatEquivalentCopy();
        copy.freeToPlayOnce = true;
        copy.purgeOnUse = true;
        markSkipDiscard(card);
        exhaustFromCurrentPile(card);

        if (copy.target == AbstractCard.CardTarget.ENEMY || copy.target == AbstractCard.CardTarget.SELF_AND_ENEMY) {
            AbstractMonster target = AbstractDungeon.getRandomMonster();
            AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(copy, target, false, true));
        } else {
            AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(copy, false, true, true));
        }
    }

    public static void replaceDiscardWithExhaust(AbstractCard card) {
        markSkipDiscard(card);
        exhaustFromCurrentPile(card);
    }

    public static void exhaustFromCurrentPile(AbstractCard card) {
        if (AbstractDungeon.player == null) {
            return;
        }
        if (AbstractDungeon.player.hand.group.contains(card)) {
            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
        } else if (AbstractDungeon.player.discardPile.group.contains(card)) {
            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, AbstractDungeon.player.discardPile));
        } else if (AbstractDungeon.handCardSelectScreen != null
                && AbstractDungeon.handCardSelectScreen.selectedCards.group.contains(card)) {
            AbstractDungeon.actionManager.addToBottom(
                    new ExhaustSpecificCardAction(card, AbstractDungeon.handCardSelectScreen.selectedCards)
            );
        }
    }

    public static void markSkipDiscard(AbstractCard card) {
        if (card != null) {
            skipDiscardCards.add(card);
        }
    }

    public static boolean shouldSkipDiscard(AbstractCard card) {
        return card != null && skipDiscardCards.remove(card);
    }
}
