package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.util.CardKeywordHelper;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RottingSlash extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("RottingSlash");

    public RottingSlash() {
        super(ID, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, 1);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseDamage = 10;
        damage = baseDamage;
        CardKeywordHelper.grantRot(this);
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
        return new RottingSlash();
    }
}

