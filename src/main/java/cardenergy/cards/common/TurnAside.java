package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.actions.SelectHandPutOnDeckAction;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TurnAside extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("TurnAside");
    private static final String SELECT_TEXT = "Put 1 card from your hand on top of your draw pile.";

    public TurnAside() {
        super(ID, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 0);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseBlock = 0;
        block = baseBlock;
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
        addToBot(new SelectHandPutOnDeckAction(p, SELECT_TEXT));
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
        return new TurnAside();
    }
}

