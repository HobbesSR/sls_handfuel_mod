package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.combat.SalvagerCombatState;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AshenCount extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("AshenCount");

    public AshenCount() {
        super(ID, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, 1);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseDamage = 8;
        damage = baseDamage;
        magicNumber = baseMagicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (m != null && SalvagerCombatState.getExhaustedThisTurn(p) > 0) {
            addToBot(new DamageAction(m, new DamageInfo(p, magicNumber, damageTypeForTurn), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new AshenCount();
    }
}

