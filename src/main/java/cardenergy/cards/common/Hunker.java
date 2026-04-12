package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Defend_Red;

public class Hunker extends Defend_Red {
    public static final String ID = CardEnergyMod.makeID("Hunker");

    public Hunker() {
        super();
        TerracottaCardHelper.applyIdentity(this, ID);
        cost = 2;
        costForTurn = 2;
        baseBlock = 13;
        block = baseBlock;
        rarity = CardRarity.COMMON;
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
        return new Hunker();
    }
}

