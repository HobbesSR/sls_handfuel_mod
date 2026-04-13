package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.util.CardKeywordHelper;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class HiddenCache extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("HiddenCache");
    private static final int BASE_BLOCK_VALUE = 6;
    private static final int HOARD_AMOUNT = 2;

    public HiddenCache() {
        super(ID, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 1);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseBlock = BASE_BLOCK_VALUE;
        block = baseBlock;
        magicNumber = baseMagicNumber = HOARD_AMOUNT;
        CardKeywordHelper.grantHoardToBlock(this, HOARD_AMOUNT);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(2);
            CardKeywordHelper.upgradeHoardToBlock(this, 1);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new HiddenCache();
    }
}

