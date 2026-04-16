package cardenergy.cards.rare;

import cardenergy.CardEnergyMod;
import cardenergy.cards.AbstractTerracottaCard;
import cardenergy.cards.TerracottaCardHelper;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Barricade;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

public class PreciousBauble extends AbstractTerracottaCard {
    public static final String ID = CardEnergyMod.makeID("PreciousBauble");
    private static final int MAX_HEALTH_LOSS = 1;

    public PreciousBauble() {
        super(ID, CardType.SKILL, CardRarity.RARE, CardTarget.SELF, -2);
        TerracottaCardHelper.applyIdentityKeepBaseStrings(this, ID, new Barricade());
        applyLocalizedStrings();
        isInnate = true;
        selfRetain = true;
        retain = true;
        keywords.add("retain");
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = "Unplayable.";
        return false;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void atTurnStart() {
        if (AbstractDungeon.player == null || !AbstractDungeon.player.hand.group.contains(this)) {
            return;
        }
        addToBot(new ApplyPowerAction(
                AbstractDungeon.player,
                AbstractDungeon.player,
                new IntangiblePlayerPower(AbstractDungeon.player, 1),
                1
        ));
    }

    @Override
    public void onRetained() {
        if (AbstractDungeon.player == null) {
            return;
        }
        AbstractDungeon.player.decreaseMaxHealth(MAX_HEALTH_LOSS);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            initializeDescription();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        return new PreciousBauble();
    }

    public static boolean isInHand(AbstractPlayer player) {
        if (player == null || player.hand == null) {
            return false;
        }
        for (AbstractCard card : player.hand.group) {
            if (card instanceof PreciousBauble) {
                return true;
            }
        }
        return false;
    }

    private void applyLocalizedStrings() {
        CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);
        this.cardID = ID;
        this.name = strings.NAME;
        this.originalName = strings.NAME;
        this.rawDescription = strings.DESCRIPTION;
        initializeDescription();
    }
}
