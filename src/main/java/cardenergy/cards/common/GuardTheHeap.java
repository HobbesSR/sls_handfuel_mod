package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.combat.SalvagerCombatState;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GuardTheHeap extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("GuardTheHeap");

    public GuardTheHeap() {
        super(ID, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 1);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseBlock = 7;
        block = baseBlock;
        magicNumber = baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        if (SalvagerCombatState.wasAttackedLastTurn(p)) {
            addToBot(new GainBlockAction(p, p, magicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(2);
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new GuardTheHeap();
    }
}

