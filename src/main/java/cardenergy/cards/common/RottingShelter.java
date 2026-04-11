package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.ShrugItOff;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RottingShelter extends ShrugItOff {
    public static final String ID = CardEnergyMod.makeID("RottingShelter");

    public RottingShelter() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 2;
        costForTurn = 2;
        baseBlock = 12;
        block = baseBlock;
        magicNumber = baseMagicNumber = 1;
        rarity = CardRarity.COMMON;
        IndigoCardHelper.addKeyword(this, "Rot");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new DrawCardAction(p, magicNumber));
    }

    @Override
    public void triggerOnManualDiscard() {
        IndigoCardHelper.queueExhaustOnDiscard(this);
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        if (AbstractDungeon.player != null && AbstractDungeon.player.hand.group.contains(this)) {
            IndigoCardHelper.exhaustFromCurrentPile(this);
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(4);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new RottingShelter();
    }
}
