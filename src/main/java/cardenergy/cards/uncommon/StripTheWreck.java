package cardenergy.cards.uncommon;

import cardenergy.CardEnergyMod;
import cardenergy.actions.SelectHandExhaustForThornsAction;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class StripTheWreck extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("StripTheWreck");
    private static final int MAX_EXHAUST = 2;

    public StripTheWreck() {
        super(ID, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE, 0);
        TerracottaCardHelper.applyIdentity(this, ID);
        magicNumber = baseMagicNumber = 2;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
        String prompt = strings.EXTENDED_DESCRIPTION != null && strings.EXTENDED_DESCRIPTION.length > 0
                ? strings.EXTENDED_DESCRIPTION[0]
                : "Exhaust up to " + MAX_EXHAUST + " cards.";
        addToBot(new SelectHandExhaustForThornsAction(p, MAX_EXHAUST, magicNumber, prompt));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new StripTheWreck();
    }
}
