package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.util.CardKeywordHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Cleave;

public class ScrapBurst extends Cleave {
    public static final String ID = CardEnergyMod.makeID("ScrapBurst");

    public ScrapBurst() {
        super();
        TerracottaCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseDamage = 3;
        damage = baseDamage;
        rarity = CardRarity.BASIC;
        CardKeywordHelper.grantConsume(this);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ScrapBurst();
    }
}

