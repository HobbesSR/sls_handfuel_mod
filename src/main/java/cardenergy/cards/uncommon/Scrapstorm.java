package cardenergy.cards.uncommon;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.cards.status.Scrap;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Scrapstorm extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("Scrapstorm");

    public Scrapstorm() {
        super(ID, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY, 1);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseDamage = 12;
        damage = baseDamage;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, multiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        addToBot(new MakeTempCardInDiscardAction(new Scrap(), 1));
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
        return new Scrapstorm();
    }
}
