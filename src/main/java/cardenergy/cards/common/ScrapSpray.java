package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Cleave;

public class ScrapSpray extends Cleave {
    public static final String ID = CardEnergyMod.makeID("ScrapSpray");

    public ScrapSpray() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseDamage = 7;
        damage = baseDamage;
        rarity = CardRarity.COMMON;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ScrapSpray();
    }
}
