package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.cards.status.Junk;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ScrapSpray extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("ScrapSpray");

    public ScrapSpray() {
        super(ID, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY, 1);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseDamage = 10;
        damage = baseDamage;
        rarity = CardRarity.COMMON;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new MakeTempCardInDiscardAction(new Junk(), 1));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ScrapSpray();
    }
}

