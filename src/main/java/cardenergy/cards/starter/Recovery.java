package cardenergy.cards.starter;

import cardenergy.actions.SelectExhaustToDiscardAction;
import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.ShrugItOff;

public class Recovery extends ShrugItOff {
    public static final String ID = CardEnergyMod.makeID("Recovery");
    private static final String SELECT_TEXT = "Return up to 2 exhausted cards to your discard pile.";

    public Recovery() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseBlock = 3;
        block = baseBlock;
        magicNumber = baseMagicNumber = 2;
        rarity = CardRarity.BASIC;
    }

    @Override
    public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, com.megacrit.cardcrawl.monsters.AbstractMonster m) {
        addToBot(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, block));
        addToBot(new SelectExhaustToDiscardAction(p, magicNumber, SELECT_TEXT));
    }

    @Override
    public AbstractCard makeCopy() {
        return new Recovery();
    }
}
