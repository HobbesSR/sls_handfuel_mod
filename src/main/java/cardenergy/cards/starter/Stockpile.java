package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.cards.red.IronWave;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Stockpile extends IronWave {
    public static final String ID = CardEnergyMod.makeID("Stockpile");
    private static final int BASE_DAMAGE_VALUE = 3;
    private static final int BASE_BLOCK_VALUE = 4;
    private static final int HOARD_AMOUNT = 4;

    public Stockpile() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 2;
        costForTurn = 2;
        baseDamage = BASE_DAMAGE_VALUE;
        baseBlock = BASE_BLOCK_VALUE;
        damage = baseDamage;
        block = baseBlock;
        rarity = CardRarity.BASIC;
        IndigoCardHelper.addKeyword(this, "Hoard");
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        if (com.megacrit.cardcrawl.dungeons.AbstractDungeon.player != null
                && com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.hand.group.contains(this)) {
            baseDamage += HOARD_AMOUNT;
            baseBlock += HOARD_AMOUNT;
            applyPowers();
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        super.use(abstractPlayer, abstractMonster);
        resetHoard();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            super.upgrade();
            resetHoard();
        }
    }

    private int getBaseDamageValue() {
        return upgraded ? BASE_DAMAGE_VALUE + 1 : BASE_DAMAGE_VALUE;
    }

    private int getBaseBlockValue() {
        return upgraded ? BASE_BLOCK_VALUE + 1 : BASE_BLOCK_VALUE;
    }

    private void resetHoard() {
        baseDamage = getBaseDamageValue();
        baseBlock = getBaseBlockValue();
        damage = baseDamage;
        block = baseBlock;
        isDamageModified = false;
        isBlockModified = false;
        initializeDescription();
    }

    @Override
    public AbstractCard makeCopy() {
        return new Stockpile();
    }
}
