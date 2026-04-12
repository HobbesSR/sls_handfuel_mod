package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

public class Brace extends Defend_Red {
    public static final String ID = CardEnergyMod.makeID("Brace");

    public Brace() {
        super();
        TerracottaCardHelper.applyIdentity(this, ID);
        cost = 2;
        costForTurn = 2;
        baseBlock = 11;
        block = baseBlock;
        magicNumber = baseMagicNumber = 1;
        rarity = CardRarity.BASIC;
        target = CardTarget.ENEMY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        if (m != null) {
            addToBot(new ApplyPowerAction(m, p, new WeakPower(m, magicNumber, false), magicNumber));
        }
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
        return new Brace();
    }
}

