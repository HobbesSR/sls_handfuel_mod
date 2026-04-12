package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.util.CardKeywordHelper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class LooseParts extends Defend_Red {
    public static final String ID = CardEnergyMod.makeID("LooseParts");

    public LooseParts() {
        super();
        TerracottaCardHelper.applyIdentity(this, ID);
        cost = 0;
        costForTurn = 0;
        baseBlock = 0;
        block = baseBlock;
        rarity = CardRarity.COMMON;
        CardKeywordHelper.grantConsume(this);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DrawCardAction(p, 1));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            selfRetain = true;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new LooseParts();
    }
}

