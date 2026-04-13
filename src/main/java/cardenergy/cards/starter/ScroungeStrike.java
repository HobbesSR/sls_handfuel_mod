package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.cards.DamageInfo;

public class ScroungeStrike extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("ScroungeStrike");

    public ScroungeStrike() {
        super(ID, CardType.ATTACK, CardRarity.BASIC, CardTarget.ENEMY, 0);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseDamage = 4;
        damage = baseDamage;
        tags.add(CardTags.STARTER_STRIKE);
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
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
        return new ScroungeStrike();
    }
}

