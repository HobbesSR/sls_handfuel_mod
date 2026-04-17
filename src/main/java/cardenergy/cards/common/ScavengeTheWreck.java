package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.actions.SelectDiscardToExhaustAction;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ScavengeTheWreck extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("ScavengeTheWreck");
    private static final String SELECT_TEXT = "Exhaust 1 card from your discard pile.";

    public ScavengeTheWreck() {
        super(ID, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 0);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseBlock = 0;
        block = baseBlock;
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectDiscardToExhaustAction(p, 1, SELECT_TEXT));
        addToBot(new DrawCardAction(p, magicNumber));
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
        return new ScavengeTheWreck();
    }
}

