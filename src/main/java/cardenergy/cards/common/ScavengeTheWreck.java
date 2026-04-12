package cardenergy.cards.common;

import cardenergy.CardEnergyMod;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.red.Defend_Red;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ScavengeTheWreck extends Defend_Red {
    public static final String ID = CardEnergyMod.makeID("ScavengeTheWreck");
    private static final String SELECT_TEXT = "Exhaust 1 card.";

    public ScavengeTheWreck() {
        super();
        TerracottaCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseBlock = 0;
        block = baseBlock;
        magicNumber = baseMagicNumber = 2;
        rarity = CardRarity.COMMON;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AbstractGameAction() {
            private boolean openedSelection;

            @Override
            public void update() {
                if (!openedSelection) {
                    if (p.hand.isEmpty()) {
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
        return new ScavengeTheWreck();
    }
}

