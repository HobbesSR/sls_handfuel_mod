package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.actions.SelectHandDiscardAction;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LooseParts extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("LooseParts");
    private static final String SELECT_TEXT = "Discard 1 card.";

    public LooseParts() {
        super(ID, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 0);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseBlock = 0;
        block = baseBlock;
        magicNumber = baseMagicNumber = 1;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
        addToBot(new SelectHandDiscardAction(p, SELECT_TEXT));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LooseParts();
    }
}

