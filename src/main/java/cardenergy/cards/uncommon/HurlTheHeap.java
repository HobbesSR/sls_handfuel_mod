package cardenergy.cards.uncommon;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HurlTheHeap extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("HurlTheHeap");

    public HurlTheHeap() {
        super(ID, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, 2);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseDamage = 0;
        damage = baseDamage;
        magicNumber = baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = baseDamage;
        baseDamage = magicNumber * getExhaustPileSizeSafe();
        super.applyPowers();
        isDamageModified = damage != baseDamage;
        baseDamage = realBaseDamage;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        int realBaseDamage = baseDamage;
        baseDamage = magicNumber * getExhaustPileSizeSafe();
        super.calculateCardDamage(mo);
        isDamageModified = damage != baseDamage;
        baseDamage = realBaseDamage;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.cost = 1;
            this.costForTurn = 1;
            this.upgradedCost = true;
            upgradeMagicNumber(-1);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new HurlTheHeap();
    }

    private int getExhaustPileSizeSafe() {
        if (AbstractDungeon.player == null || AbstractDungeon.player.exhaustPile == null) {
            return 0;
        }
        return AbstractDungeon.player.exhaustPile.size();
    }
}
