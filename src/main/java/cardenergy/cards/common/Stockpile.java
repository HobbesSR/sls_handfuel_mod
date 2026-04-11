package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.IronWave;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Stockpile extends IronWave {
    public static final String ID = CardEnergyMod.makeID("Stockpile");
    private static final int BASE_DAMAGE_VALUE = 4;
    private static final int BASE_BLOCK_VALUE = 5;
    private static final int HOARD_AMOUNT = 3;

    public Stockpile() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 2;
        costForTurn = 2;
        baseDamage = BASE_DAMAGE_VALUE;
        baseBlock = BASE_BLOCK_VALUE;
        damage = baseDamage;
        block = baseBlock;
        rarity = CardRarity.COMMON;
        IndigoCardHelper.addKeyword(this, "Hoard");
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        if (AbstractDungeon.player != null && AbstractDungeon.player.hand.group.contains(this)) {
            baseDamage += HOARD_AMOUNT;
            baseBlock += HOARD_AMOUNT;
            applyPowers();
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        resetHoard();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(1);
            upgradeBlock(1);
            resetHoard();
            initializeDescription();
        }
    }

    private void resetHoard() {
        baseDamage = upgraded ? BASE_DAMAGE_VALUE + 1 : BASE_DAMAGE_VALUE;
        baseBlock = upgraded ? BASE_BLOCK_VALUE + 1 : BASE_BLOCK_VALUE;
        damage = baseDamage;
        block = baseBlock;
        isDamageModified = false;
        isBlockModified = false;
    }

    @Override
    public AbstractCard makeCopy() {
        return new Stockpile();
    }
}
