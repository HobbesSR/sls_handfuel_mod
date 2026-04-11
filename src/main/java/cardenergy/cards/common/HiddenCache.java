package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class HiddenCache extends Defend_Red {
    public static final String ID = CardEnergyMod.makeID("HiddenCache");
    private static final int BASE_BLOCK_VALUE = 6;
    private static final int HOARD_AMOUNT = 2;

    public HiddenCache() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseBlock = BASE_BLOCK_VALUE;
        block = baseBlock;
        rarity = CardRarity.COMMON;
        IndigoCardHelper.addKeyword(this, "Hoard");
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        if (AbstractDungeon.player != null && AbstractDungeon.player.hand.group.contains(this)) {
            baseBlock += HOARD_AMOUNT;
            applyPowers();
        }
    }

    @Override
    public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, com.megacrit.cardcrawl.monsters.AbstractMonster m) {
        super.use(p, m);
        resetHoard();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(2);
            resetHoard();
            initializeDescription();
        }
    }

    private void resetHoard() {
        baseBlock = upgraded ? BASE_BLOCK_VALUE + 2 : BASE_BLOCK_VALUE;
        block = baseBlock;
        isBlockModified = false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new HiddenCache();
    }
}
