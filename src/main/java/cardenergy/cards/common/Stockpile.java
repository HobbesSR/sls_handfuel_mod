package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.util.CardKeywordHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.IronWave;

public class Stockpile extends IronWave {
    public static final String ID = CardEnergyMod.makeID("Stockpile");
    private static final int BASE_DAMAGE_VALUE = 4;
    private static final int BASE_BLOCK_VALUE = 5;
    private static final int HOARD_AMOUNT = 3;

    public Stockpile() {
        super();
        TerracottaCardHelper.applyIdentity(this, ID);
        cost = 2;
        costForTurn = 2;
        baseDamage = BASE_DAMAGE_VALUE;
        baseBlock = BASE_BLOCK_VALUE;
        damage = baseDamage;
        block = baseBlock;
        magicNumber = baseMagicNumber = HOARD_AMOUNT;
        rarity = CardRarity.COMMON;
        CardKeywordHelper.grantHoardToDamageAndBlock(this, HOARD_AMOUNT);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(1);
            upgradeBlock(1);
            CardKeywordHelper.upgradeHoardToDamageAndBlock(this, 1);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Stockpile();
    }
}

