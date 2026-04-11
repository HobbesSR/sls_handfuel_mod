package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Cleave;

public class ScrapBurst extends Cleave {
    public static final String ID = CardEnergyMod.makeID("ScrapBurst");

    public ScrapBurst() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseDamage = 5;
        damage = baseDamage;
        rarity = CardRarity.BASIC;
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
            upgradeDamage(3);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ScrapBurst();
    }
}
