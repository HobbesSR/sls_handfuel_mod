package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Strike_Red;

public class RottingBlow extends Strike_Red {
    public static final String ID = CardEnergyMod.makeID("RottingBlow");

    public RottingBlow() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseDamage = 10;
        damage = baseDamage;
        rarity = CardRarity.BASIC;
        IndigoCardHelper.addKeyword(this, "Rot");
    }

    @Override
    public void triggerOnManualDiscard() {
        IndigoCardHelper.queueExhaustOnDiscard(this);
    }

    @Override
    public void triggerOnEndOfPlayerTurn() {
        if (com.megacrit.cardcrawl.dungeons.AbstractDungeon.player != null
                && com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.hand.group.contains(this)) {
            IndigoCardHelper.exhaustFromCurrentPile(this);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new RottingBlow();
    }
}
