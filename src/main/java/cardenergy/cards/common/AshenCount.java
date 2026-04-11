package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import cardenergy.combat.ScavengerCombatState;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AshenCount extends Strike_Red {
    public static final String ID = CardEnergyMod.makeID("AshenCount");

    public AshenCount() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseDamage = 5;
        damage = baseDamage;
        magicNumber = baseMagicNumber = 5;
        rarity = CardRarity.COMMON;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        if (m != null && ScavengerCombatState.getExhaustedThisTurn(p) > 0) {
            addToBot(new DamageAction(m, new DamageInfo(p, magicNumber, damageTypeForTurn), com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            upgradeMagicNumber(2);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new AshenCount();
    }
}
