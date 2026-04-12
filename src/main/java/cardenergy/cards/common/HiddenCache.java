package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.util.CardKeywordHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Defend_Red;

public class HiddenCache extends Defend_Red {
    public static final String ID = CardEnergyMod.makeID("HiddenCache");
    private static final int BASE_BLOCK_VALUE = 6;
    private static final int HOARD_AMOUNT = 2;

    public HiddenCache() {
        super();
        TerracottaCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseBlock = BASE_BLOCK_VALUE;
        block = baseBlock;
        magicNumber = baseMagicNumber = HOARD_AMOUNT;
        rarity = CardRarity.COMMON;
        CardKeywordHelper.grantHoardToBlock(this, HOARD_AMOUNT);
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

