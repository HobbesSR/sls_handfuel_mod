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

public class Junk extends CustomCard {
    public static final String ID = CardEnergyMod.makeID("Junk");
    private static final CardStrings STRINGS = CardCrawlGame.languagePack.getCardStrings(ID);

    public Junk() {
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
        if (JunkScrapCardHelper.countJunkOrScrapInHand(p) < 3) {
            this.cantUseMessage = "Need at least 2 other Junk or Scrap cards in hand.";
            return false;
        }
        return true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        ArrayList<AbstractCard> handSnapshot = new ArrayList<>(p.hand.group);
        for (AbstractCard card : handSnapshot) {
            if (JunkScrapCardHelper.isJunkOrScrap(card)) {
                addToBot(new ExhaustSpecificCardAction(card, p.hand));
            }
        }

        AbstractCard reward = JunkScrapCardHelper.getRandomTerracottaByRarity(CardRarity.UNCOMMON);
        if (reward != null) {
            addToBot(new MakeTempCardInHandAction(reward, 1));
        }
    }

    @Override
    public void upgrade() {
    }

    @Override
    public AbstractCard makeCopy() {
        return new Junk();
    }
}
