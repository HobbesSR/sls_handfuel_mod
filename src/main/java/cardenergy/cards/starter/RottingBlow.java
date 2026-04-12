package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.util.CardKeywordHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Strike_Red;

public class RottingBlow extends Strike_Red {
    public static final String ID = CardEnergyMod.makeID("RottingBlow");

    public RottingBlow() {
        super();
        TerracottaCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseDamage = 10;
        damage = baseDamage;
        rarity = CardRarity.BASIC;
        CardKeywordHelper.grantRot(this);
    }

    @Override
    public AbstractCard makeCopy() {
        return new RottingBlow();
    }
}

