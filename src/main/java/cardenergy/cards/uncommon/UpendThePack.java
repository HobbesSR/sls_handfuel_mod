package cardenergy.cards.uncommon;

import cardenergy.CardEnergyMod;
import cardenergy.actions.DiscardEntireHandAction;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class UpendThePack extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("UpendThePack");

    public UpendThePack() {
        super(ID, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF, 0);
        TerracottaCardHelper.applyIdentity(this, ID);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int handSize = p.hand.size();
        addToBot(new DiscardEntireHandAction(p));
        if (handSize > 0) {
            addToBot(new DrawCardAction(p, handSize));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            exhaust = false;
            rawDescription = CardCrawlGame.languagePack.getCardStrings(ID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new UpendThePack();
    }
}
