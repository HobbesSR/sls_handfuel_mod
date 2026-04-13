package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.util.CardKeywordHelper;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Stockpile extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("Stockpile");
    private static final int BASE_DAMAGE_VALUE = 4;
    private static final int BASE_BLOCK_VALUE = 5;
    private static final int HOARD_AMOUNT = 3;

    public Stockpile() {
        super(ID, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY, 2);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseDamage = BASE_DAMAGE_VALUE;
        baseBlock = BASE_BLOCK_VALUE;
        damage = baseDamage;
        block = baseBlock;
        magicNumber = baseMagicNumber = HOARD_AMOUNT;
        CardKeywordHelper.grantHoardToDamageAndBlock(this, HOARD_AMOUNT);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn)));
        addToBot(new GainBlockAction(p, p, block));
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

