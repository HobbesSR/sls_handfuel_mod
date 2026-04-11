package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Defend_Red;

public class BurnThrough extends Defend_Red {
    public static final String ID = CardEnergyMod.makeID("BurnThrough");

    public BurnThrough() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseBlock = 6;
        block = baseBlock;
        rarity = CardRarity.COMMON;
        IndigoCardHelper.addKeyword(this, "Consume");
    }

    @Override
    public void triggerOnManualDiscard() {
        IndigoCardHelper.queueConsumeOnDiscard(this);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BurnThrough();
    }
}
