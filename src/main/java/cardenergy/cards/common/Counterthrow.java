package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.powers.CounterthrowPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Counterthrow extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("Counterthrow");

    public Counterthrow() {
        super(ID, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE, 2);
        TerracottaCardHelper.applyIdentity(this, ID);
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new CounterthrowPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.cost = 1;
            this.costForTurn = 1;
            this.upgradedCost = true;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Counterthrow();
    }
}
