package cardenergy.cards.common;

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
        super(ID, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, 1);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseDamage = 0;
        damage = baseDamage;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
    }

    @Override
    public void applyPowers() {
        baseDamage = AbstractDungeon.player.currentBlock;
        super.applyPowers();
        isDamageModified = false;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        baseDamage = AbstractDungeon.player.currentBlock;
        super.calculateCardDamage(mo);
        isDamageModified = false;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.cost = 0;
            this.costForTurn = 0;
            this.upgradedCost = true;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new HurlTheHeap();
    }
}
