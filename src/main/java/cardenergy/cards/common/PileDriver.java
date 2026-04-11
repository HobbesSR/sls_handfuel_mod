package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Strike_Red;

public class PileDriver extends Strike_Red {
    public static final String ID = CardEnergyMod.makeID("PileDriver");

    public PileDriver() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 2;
        costForTurn = 2;
        baseDamage = 12;
        damage = baseDamage;
        rarity = CardRarity.COMMON;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(4);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PileDriver();
    }
}
