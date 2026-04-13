package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ScroungeDefend extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("ScroungeDefend");

    public ScroungeDefend() {
        super(ID, CardType.SKILL, CardRarity.BASIC, CardTarget.SELF, 0);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseBlock = 5;
        block = baseBlock;
        tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
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
        return new ScroungeDefend();
    }
}

