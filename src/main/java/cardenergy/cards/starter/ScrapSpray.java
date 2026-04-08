package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Cleave;

public class ScrapSpray extends Cleave {
    public static final String ID = CardEnergyMod.makeID("ScrapSpray");
    private final boolean consume = true;

    public ScrapSpray() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 2;
        costForTurn = 2;
        baseDamage = 3;
        damage = baseDamage;
        rarity = CardRarity.BASIC;
        IndigoCardHelper.addKeyword(this, "Consume");
    }

    @Override
    public void triggerOnManualDiscard() {
        if (consume) {
            IndigoCardHelper.triggerConsume(this);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ScrapSpray();
    }
}
