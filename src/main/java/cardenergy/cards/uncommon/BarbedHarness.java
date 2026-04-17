package cardenergy.cards.uncommon;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ThornsPower;

public class BarbedHarness extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("BarbedHarness");

    public BarbedHarness() {
        super(ID, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF, 1);
        TerracottaCardHelper.applyIdentity(this, ID);
        magicNumber = baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ThornsPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(2);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new BarbedHarness();
    }
}
