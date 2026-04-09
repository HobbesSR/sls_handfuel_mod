package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Defend_Red;

public class ScroungeDefend extends Defend_Red {
    public static final String ID = CardEnergyMod.makeID("ScroungeDefend");
    private final boolean consume = true;

    public ScroungeDefend() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 0;
        costForTurn = 0;
        upgradedCost = false;
        baseBlock = 5;
        block = baseBlock;
        IndigoCardHelper.addKeyword(this, "Consume");
        tags.add(CardTags.STARTER_DEFEND);
    }

    @Override
    public void triggerOnManualDiscard() {
        if (consume) {
            IndigoCardHelper.queueConsumeOnDiscard(this);
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new ScroungeDefend();
    }
}
