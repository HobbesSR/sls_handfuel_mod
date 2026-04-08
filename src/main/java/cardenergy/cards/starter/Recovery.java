package cardenergy.cards.starter;

import cardenergy.CardEnergyMod;
import cardenergy.cards.IndigoCardHelper;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.ShrugItOff;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class Recovery extends ShrugItOff {
    public static final String ID = CardEnergyMod.makeID("Recovery");

    public Recovery() {
        super();
        IndigoCardHelper.applyIdentity(this, ID);
        cost = 1;
        costForTurn = 1;
        baseBlock = 6;
        block = baseBlock;
        magicNumber = baseMagicNumber = 2;
        rarity = CardRarity.BASIC;
    }

    @Override
    public void use(com.megacrit.cardcrawl.characters.AbstractPlayer p, com.megacrit.cardcrawl.monsters.AbstractMonster m) {
        addToBot(new com.megacrit.cardcrawl.actions.common.GainBlockAction(p, p, block));
        ArrayList<AbstractCard> exhaustedBasics = new ArrayList<>();
        for (AbstractCard card : AbstractDungeon.player.exhaustPile.group) {
            if (card.tags.contains(CardTags.STARTER_STRIKE) || card.tags.contains(CardTags.STARTER_DEFEND)) {
                exhaustedBasics.add(card);
            }
        }
        for (int i = 0; i < exhaustedBasics.size() && i < magicNumber; ++i) {
            AbstractDungeon.player.exhaustPile.moveToDiscardPile(exhaustedBasics.get(i));
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new Recovery();
    }
}
