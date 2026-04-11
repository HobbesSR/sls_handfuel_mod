package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.IronWave;

public class PackedSwing extends IronWave {
    public static final String ID = CardEnergyMod.makeID("PackedSwing");

    public PackedSwing() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseDamage = 6;
        baseBlock = 4;
        damage = baseDamage;
        block = baseBlock;
        rarity = CardRarity.COMMON;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeBlock(2);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PackedSwing();
    }
}
