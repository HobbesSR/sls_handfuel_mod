package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import cardenergy.cards.status.Junk;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Patchwork extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("Patchwork");
    private static final String SELECT_TEXT = "Exhaust 1 card.";

    public Patchwork() {
        super(ID, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF, 1);
        TerracottaCardHelper.applyIdentity(this, ID);
        baseBlock = 0;
        block = baseBlock;
        magicNumber = baseMagicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            private boolean openedSelection;

            @Override
            public void update() {
                if (!openedSelection) {
                    if (p.hand.isEmpty()) {
                        addToTop(new MakeTempCardInDiscardAction(new Junk(), 1));
                        addToTop(new DrawCardAction(p, magicNumber));
                        isDone = true;
                        return;
                    }
                    AbstractDungeon.handCardSelectScreen.open(SELECT_TEXT, 1, false, false, false, false);
                    openedSelection = true;
                    return;
                }

                if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                    if (!AbstractDungeon.handCardSelectScreen.selectedCards.isEmpty()) {
                        CardGroup selected = AbstractDungeon.handCardSelectScreen.selectedCards;
                        AbstractCard card = selected.getTopCard();
                        p.hand.addToTop(card);
                        selected.clear();
                        p.hand.refreshHandLayout();
                        addToTop(new MakeTempCardInDiscardAction(new Junk(), 1));
                        addToTop(new DrawCardAction(p, magicNumber));
                        addToTop(new ExhaustSpecificCardAction(card, p.hand));
                    }
                    AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
                    isDone = true;
                }
            }
        });
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
        return new Patchwork();
    }
}

