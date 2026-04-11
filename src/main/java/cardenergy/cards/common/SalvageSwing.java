package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import cardenergy.combat.ScavengerCombatState;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SalvageSwing extends Strike_Red {
    public static final String ID = CardEnergyMod.makeID("SalvageSwing");

    public SalvageSwing() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseDamage = 7;
        damage = baseDamage;
        magicNumber = baseMagicNumber = 3;
        rarity = CardRarity.COMMON;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        if (ScavengerCombatState.getExhaustedThisTurn(p) > 0) {
            addToBot(new GainBlockAction(p, p, magicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new SalvageSwing();
    }
}
