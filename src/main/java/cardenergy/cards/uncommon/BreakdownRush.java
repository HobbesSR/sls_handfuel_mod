package cardenergy.cards.uncommon;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.combat.SalvagerCombatState;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BreakdownRush extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("BreakdownRush");
    private static final int PRINTED_DAMAGE = 14;

    public BreakdownRush() {
        super(ID, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, 2);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseDamage = PRINTED_DAMAGE;
        damage = baseDamage;
        magicNumber = baseMagicNumber = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public void applyPowers() {
        int bonus = magicNumber * SalvagerCombatState.getExhaustedThisTurn(AbstractDungeon.player);
        baseDamage = PRINTED_DAMAGE + bonus;
        super.applyPowers();
        isDamageModified = bonus > 0;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int bonus = magicNumber * SalvagerCombatState.getExhaustedThisTurn(AbstractDungeon.player);
        baseDamage = PRINTED_DAMAGE + bonus;
        super.calculateCardDamage(mo);
        isDamageModified = bonus > 0;
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
        return new BreakdownRush();
    }
}
