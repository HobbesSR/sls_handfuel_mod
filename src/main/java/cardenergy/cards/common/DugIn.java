package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.cards.status.Junk;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DugIn extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("DugIn");

    public DugIn() {
        super(ID, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 1);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseBlock = 10;
        block = baseBlock;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new MakeTempCardInDiscardAction(new Junk(), 1));
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
        return new DugIn();
    }
}

