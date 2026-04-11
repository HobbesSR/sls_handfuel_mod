package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.actions.SelectExhaustToDiscardAction;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.ShrugItOff;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Patchwork extends ShrugItOff {
    public static final String ID = CardEnergyMod.makeID("Patchwork");
    private static final String SELECT_TEXT = "Return 1 exhausted card to your discard pile.";

    public Patchwork() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseBlock = 5;
        block = baseBlock;
        magicNumber = baseMagicNumber = 1;
        rarity = CardRarity.COMMON;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new SelectExhaustToDiscardAction(p, 1, SELECT_TEXT));
        addToBot(new DrawCardAction(p, magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(3);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Patchwork();
    }
}
