package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import cardenergy.combat.ScavengerCombatState;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class BarbedGuard extends Defend_Red {
    public static final String ID = CardEnergyMod.makeID("BarbedGuard");

    public BarbedGuard() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseBlock = 7;
        block = baseBlock;
        rarity = CardRarity.COMMON;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        ScavengerCombatState.addPendingBarbedGuard(p, 1);
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
        return new BarbedGuard();
    }
}
