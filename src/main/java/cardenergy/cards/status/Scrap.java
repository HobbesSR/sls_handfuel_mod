package cardenergy.cards.status;

import basemod.abstracts.CustomCard;
import cardenergy.CardEnergyMod;
import cardenergy.util.JunkScrapCardHelper;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class Scrap extends CustomCard {
    public static final String ID = CardEnergyMod.makeID("Scrap");
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public Scrap() {
        super(
                ID,
                STRINGS.NAME,
                (String) null,
                0,
                STRINGS.DESCRIPTION,
                CardType.STATUS,
                CardColor.COLORLESS,
                CardRarity.SPECIAL,
                CardTarget.SELF
        );
        this.exhaust = true;
        this.freeToPlayOnce = true;
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.freeToPlayOnce = true;
        if (!super.canUse(p, m)) {
            return false;
        }
        if (JunkScrapCardHelper.countScrapInHand(p) < 2) {
            this.cantUseMessage = "Need at least 1 other Scrap card in hand.";
            return false;
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> handSnapshot = new ArrayList<>(p.hand.group);
        for (AbstractCard card : handSnapshot) {
            if (JunkScrapCardHelper.isScrap(card)) {
                addToBot(new ExhaustSpecificCardAction(card, p.hand));
            }
        }

        AbstractCard reward = JunkScrapCardHelper.getRandomTerracottaByRarity(CardRarity.RARE);
        if (reward != null) {
            addToBot(new MakeTempCardInHandAction(reward, 1));
        }
    }

    @Override
    public void upgrade() {
    }

    @Override
    public AbstractCard makeCopy() {
        return new Scrap();
    }
}
