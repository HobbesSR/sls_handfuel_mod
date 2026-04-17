package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;

public class BraceForImpact extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("BraceForImpact");

    public BraceForImpact() {
        super(ID, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 1);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseBlock = 9;
        block = baseBlock;
        magicNumber = baseMagicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
        addToBot(new ApplyPowerAction(p, p, new ArtifactPower(p, magicNumber), magicNumber));
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
        return new BraceForImpact();
    }
}

