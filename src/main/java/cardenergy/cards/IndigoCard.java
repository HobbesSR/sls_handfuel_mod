package cardenergy.cards;

import basemod.abstracts.CustomCard;
import basemod.helpers.TooltipInfo;
import cardenergy.CardEnergyMod;
import cardenergy.character.CardEnergyCharacterEnum;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Set;

public abstract class IndigoCard extends CustomCard {
    protected static final String ATTACK_IMG = CardEnergyMod.CARD_IMG_DIR + "/attack.png";
    protected static final String SKILL_IMG = CardEnergyMod.CARD_IMG_DIR + "/skill.png";
    protected static final String POWER_IMG = CardEnergyMod.CARD_IMG_DIR + "/power.png";
    protected static final String CONSUME_NAME = "Consume";
    protected static final String CONSUME_DESCRIPTION = "When this card is discarded from your hand, play it automatically after the current card resolves. Exhaust it.";
    protected static final String HOARD_NAME = "Hoard";
    protected static final String ROT_NAME = "Rot";
    protected static final String ROT_DESCRIPTION = "If this card is discarded or remains in your hand at end of turn, Exhaust it.";
    private static final Set<AbstractCard> skipDiscardCards =
            Collections.newSetFromMap(new IdentityHashMap<AbstractCard, Boolean>());

    protected boolean consume;
    protected boolean rot;
    protected int hoardAmount;

    protected IndigoCard(String id, CardType type, CardRarity rarity, CardTarget target, int cost) {
        super(id, getCardStrings(id).NAME, getImagePath(type), cost, getCardStrings(id).DESCRIPTION, type, CardEnergyCharacterEnum.INDIGO, rarity, target);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> tooltips = new ArrayList<>();
        if (consume) {
            tooltips.add(new TooltipInfo(CONSUME_NAME, CONSUME_DESCRIPTION));
        }
        if (hoardAmount > 0) {
            tooltips.add(new TooltipInfo(HOARD_NAME, "At the end of your turn, if this card is in your hand, it gains +" + hoardAmount + " damage and Block."));
        }
        if (rot) {
            tooltips.add(new TooltipInfo(ROT_NAME, ROT_DESCRIPTION));
        }
        return tooltips;
    }

    @Override
    public void triggerOnManualDiscard() {
        if (consume) {
            triggerConsume();
        } else if (rot) {
            replaceDiscardWithExhaust();
        }
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        if (hoardAmount > 0 && AbstractDungeon.player != null && AbstractDungeon.player.hand.group.contains(this)) {
            baseDamage += hoardAmount;
            baseBlock += hoardAmount;
            applyPowers();
        }
        if (rot && AbstractDungeon.player != null && AbstractDungeon.player.hand.group.contains(this)) {
            addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        }
    }

    protected void triggerConsume() {
        AbstractCard copy = makeStatEquivalentCopy();
        copy.freeToPlayOnce = true;
        copy.purgeOnUse = true;
        markSkipDiscard(this);
        moveToExhaustPileImmediately();

        if (copy.target == CardTarget.ENEMY || copy.target == CardTarget.SELF_AND_ENEMY) {
            AbstractMonster target = AbstractDungeon.getRandomMonster();
            addToBot(new NewQueueCardAction(copy, target, false, true));
        } else {
            addToBot(new NewQueueCardAction(copy, false, true, true));
        }
    }

    protected void exhaustFromCurrentPile() {
        if (AbstractDungeon.player == null) {
            return;
        }
        if (AbstractDungeon.player.hand.group.contains(this)) {
            addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.hand));
        } else if (AbstractDungeon.player.discardPile.group.contains(this)) {
            addToBot(new ExhaustSpecificCardAction(this, AbstractDungeon.player.discardPile));
        }
    }

    protected void replaceDiscardWithExhaust() {
        markSkipDiscard(this);
        moveToExhaustPileImmediately();
    }

    protected void moveToExhaustPileImmediately() {
        if (AbstractDungeon.player == null) {
            return;
        }
        if (AbstractDungeon.player.hand.group.contains(this)) {
            AbstractDungeon.player.hand.moveToExhaustPile(this);
        } else if (AbstractDungeon.player.discardPile.group.contains(this)) {
            AbstractDungeon.player.discardPile.moveToExhaustPile(this);
        } else if (AbstractDungeon.handCardSelectScreen != null
                && AbstractDungeon.handCardSelectScreen.selectedCards.group.contains(this)) {
            AbstractDungeon.handCardSelectScreen.selectedCards.moveToExhaustPile(this);
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

    private static CardStrings getCardStrings(String id) {
        return CardCrawlGame.languagePack.getCardStrings(id);
    }

    protected void addKeyword(String keyword) {
        keywords.add(keyword.toLowerCase());
    }

    private static String getImagePath(CardType type) {
        if (type == CardType.ATTACK) {
            return ATTACK_IMG;
        }
        if (type == CardType.POWER) {
            return POWER_IMG;
        }
        return SKILL_IMG;
    }
}
