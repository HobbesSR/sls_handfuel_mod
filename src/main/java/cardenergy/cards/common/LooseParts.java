package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.cards.status.Junk;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LooseParts extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("LooseParts");

    public LooseParts() {
        super(ID, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 0);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseBlock = 0;
        block = baseBlock;
        magicNumber = baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, magicNumber));
        addToBot(new MakeTempCardInDiscardAction(new Junk(), 1));
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

