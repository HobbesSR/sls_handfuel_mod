package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Defend_Red;

public class BraceForImpact extends Defend_Red {
    public static final String ID = CardEnergyMod.makeID("BraceForImpact");

    public BraceForImpact() {
        super();
        TerracottaCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseBlock = 8;
        block = baseBlock;
        rarity = CardRarity.COMMON;
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
        return new BraceForImpact();
    }
}

