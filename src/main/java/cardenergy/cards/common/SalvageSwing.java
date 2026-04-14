package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.combat.SalvagerCombatState;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SalvageSwing extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("SalvageSwing");

    public SalvageSwing() {
        super(ID, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, 1);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseDamage = 7;
        damage = baseDamage;
        magicNumber = baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
        if (SalvagerCombatState.getExhaustedThisTurn(p) > 0) {
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

