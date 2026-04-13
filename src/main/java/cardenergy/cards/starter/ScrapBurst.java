package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.util.CardKeywordHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ScrapBurst extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("ScrapBurst");

    public ScrapBurst() {
        super(ID, CardType.ATTACK, CardRarity.BASIC, CardTarget.ALL_ENEMY, 1);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseDamage = 3;
        damage = baseDamage;
        isMultiDamage = true;
        CardKeywordHelper.grantConsume(this);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(2);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ScrapBurst();
    }
}

