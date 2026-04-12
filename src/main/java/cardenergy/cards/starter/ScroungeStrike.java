package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Strike_Red;

public class ScroungeStrike extends Strike_Red {
    public static final String ID = CardEnergyMod.makeID("ScroungeStrike");

    public ScroungeStrike() {
        super();
        TerracottaCardHelper.applyIdentity(this, ID);
        cost = 0;
        costForTurn = 0;
        baseDamage = 4;
        damage = baseDamage;
        upgradedCost = false;
        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public AbstractCard makeCopy() {
        return new ScroungeStrike();
    }
}

