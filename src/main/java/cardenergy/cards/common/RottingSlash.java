package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Strike_Red;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class RottingSlash extends Strike_Red {
    public static final String ID = CardEnergyMod.makeID("RottingSlash");

    public RottingSlash() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseDamage = 8;
        damage = baseDamage;
        rarity = CardRarity.COMMON;
        IndigoCardHelper.addKeyword(this, "Rot");
    }

    @Override
    public void triggerOnManualDiscard() {
        IndigoCardHelper.queueExhaustOnDiscard(this);
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        if (AbstractDungeon.player != null && AbstractDungeon.player.hand.group.contains(this)) {
            IndigoCardHelper.exhaustFromCurrentPile(this);
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(3);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new RottingSlash();
    }
}
