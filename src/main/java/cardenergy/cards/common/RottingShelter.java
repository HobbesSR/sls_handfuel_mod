package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.util.CardKeywordHelper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class RottingShelter extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("RottingShelter");

    public RottingShelter() {
        super(ID, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 2);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseBlock = 12;
        block = baseBlock;
        magicNumber = baseMagicNumber = 1;
        CardKeywordHelper.grantRot(this);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new DrawCardAction(p, magicNumber));
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
        return new RottingShelter();
    }
}

