package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.IronWave;

public class Stockpile extends IronWave {
    public static final String ID = CardEnergyMod.makeID("Stockpile");
    private static final int BASE_DAMAGE_VALUE = 6;
    private static final int BASE_BLOCK_VALUE = 6;
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
    public void onMoveToDiscard() {
        resetHoard();
    }

    @Override
    public void triggerOnExhaust() {
        resetHoard();
    }

    @Override
    public void resetAttributes() {
        super.resetAttributes();
        if (com.megacrit.cardcrawl.dungeons.AbstractDungeon.player == null
                || !com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.hand.group.contains(this)) {
            resetHoard();
        }
    }

    private void resetHoard() {
        baseDamage = BASE_DAMAGE_VALUE;
        baseBlock = BASE_BLOCK_VALUE;
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
