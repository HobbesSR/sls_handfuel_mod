package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Hunker extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("Hunker");

    public Hunker() {
        super(ID, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 2);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseBlock = 13;
        block = baseBlock;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(4);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Hunker();
    }
}

