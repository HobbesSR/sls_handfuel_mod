package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.ShrugItOff;

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
        rarity = CardRarity.BASIC;
        IndigoCardHelper.addKeyword(this, "Rot");
    }

    @Override
    public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, com.megacrit.cardcrawl.monsters.AbstractMonster m) {
        addToBot(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, block));
        addToBot(new com.megacrit.cardcrawl.actions.common.DrawCardAction(p, magicNumber));
    }

    @Override
    public void triggerOnManualDiscard() {
        IndigoCardHelper.queueExhaustOnDiscard(this);
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        if (com.megacrit.cardcrawl.dungeons.AbstractDungeon.player != null
                && com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.hand.group.contains(this)) {
            IndigoCardHelper.exhaustFromCurrentPile(this);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new RottingShelter();
    }
}
