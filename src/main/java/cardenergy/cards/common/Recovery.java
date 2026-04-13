package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.actions.SelectExhaustToDiscardAction;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Recovery extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("Recovery");
    private static final String SELECT_TEXT = "Return up to 2 exhausted cards to your discard pile.";

    public Recovery() {
        super(ID, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 0);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseBlock = 3;
        block = baseBlock;
        magicNumber = baseMagicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new SelectExhaustToDiscardAction(p, magicNumber, SELECT_TEXT));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(2);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Recovery();
    }
}

