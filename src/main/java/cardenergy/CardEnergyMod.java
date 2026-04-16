package cardenergy;

import basemod.BaseMod;
import basemod.helpers.KeywordColorInfo;
import basemod.interfaces.EditCardsSubscriber;
import basemod.interfaces.EditCharactersSubscriber;
import basemod.interfaces.EditKeywordsSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import cardenergy.cards.common.AshenCount;
import cardenergy.cards.common.BarbedGuard;
import cardenergy.cards.common.BraceForImpact;
import cardenergy.cards.common.BurnThrough;
import cardenergy.cards.common.DugIn;
import cardenergy.cards.common.GuardTheHeap;
import cardenergy.cards.common.HiddenCache;
import cardenergy.cards.common.Hunker;
import cardenergy.cards.common.LooseParts;
import cardenergy.cards.common.PackedSwing;
import cardenergy.cards.common.Patchwork;
import cardenergy.cards.common.PileDriver;
import cardenergy.cards.common.Recovery;
import cardenergy.cards.common.RottingShelter;
import cardenergy.cards.common.RottingSlash;
import cardenergy.cards.common.SalvageSwing;
import cardenergy.cards.common.ScavengeTheWreck;
import cardenergy.cards.common.ScrapKnife;
import cardenergy.cards.common.ScrapSpray;
import cardenergy.cards.common.Stockpile;
import cardenergy.cards.common.TurnAside;
import cardenergy.cards.rare.PreciousBauble;
import cardenergy.character.CardEnergyCharacter;
import cardenergy.character.CardEnergyCharacterEnum;
import cardenergy.character.TerracottaColorScheme;
import cardenergy.cards.RedMirrorCards;
import cardenergy.cards.starter.Brace;
import cardenergy.cards.starter.RottingBlow;
import cardenergy.cards.starter.ScrapBurst;
import cardenergy.cards.starter.ScroungeDefend;
import cardenergy.cards.starter.ScroungeStrike;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class CardEnergyMod implements EditCharactersSubscriber, EditCardsSubscriber, EditStringsSubscriber, EditKeywordsSubscriber {
    public static final Logger logger = LogManager.getLogger(CardEnergyMod.class.getName());

    public static final String MOD_ID = "cardenergy";
    public static final String IMG_DIR = "img/cardenergy";
    public static final String BUTTON_IMAGE = IMG_DIR + "/charSelect/handfuelButton.png";
    public static final String PORTRAIT_IMAGE = IMG_DIR + "/charSelect/handfuelPortrait.jpg";
    public static final String CARD_IMG_DIR = IMG_DIR + "/cards";
    public static final String CARD_BACK_DIR = IMG_DIR + "/cardback";
    public static final String LOCALIZATION_DIR = "localization/eng";

    public CardEnergyMod() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        TerracottaColorScheme.register();
        logger.info("Initializing Salvager build channel={} version={}", BuildInfo.getDisplay(), BuildInfo.getVersion());
        new CardEnergyMod();
    }

    public static String makeID(String name) {
        return MOD_ID + ":" + name;
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(
                new CardEnergyCharacter(CardCrawlGame.playerName),
                BUTTON_IMAGE,
                PORTRAIT_IMAGE,
                CardEnergyCharacterEnum.HAND_FUEL_TERRACOTTA
        );
        logger.info("Registered hand-fuel terracotta salvager");
    }

    @Override
    public void receiveEditCards() {
        RedMirrorCards.registerAll();
        addAndUnlock(new ScroungeStrike());
        addAndUnlock(new ScroungeDefend());
        addAndUnlock(new Brace());
        addAndUnlock(new ScrapBurst());
        addAndUnlock(new RottingBlow());
        addAndUnlock(new Recovery());
        addAndUnlock(new RottingShelter());
        addAndUnlock(new Stockpile());
        addAndUnlock(new ScrapSpray());
        addAndUnlock(new RottingSlash());
        addAndUnlock(new SalvageSwing());
        addAndUnlock(new PileDriver());
        addAndUnlock(new BraceForImpact());
        addAndUnlock(new GuardTheHeap());
        addAndUnlock(new Hunker());
        addAndUnlock(new DugIn());
        addAndUnlock(new ScrapKnife());
        addAndUnlock(new BurnThrough());
        addAndUnlock(new LooseParts());
        addAndUnlock(new AshenCount());
        addAndUnlock(new Patchwork());
        addAndUnlock(new BarbedGuard());
        addAndUnlock(new TurnAside());
        addAndUnlock(new HiddenCache());
        addAndUnlock(new ScavengeTheWreck());
        addAndUnlock(new PackedSwing());
        addAndUnlock(new PreciousBauble());
    }

    private static void addAndUnlock(com.megacrit.cardcrawl.cards.AbstractCard card) {
        BaseMod.addCard(card);
        UnlockTracker.unlockCard(card.cardID);
    }

    @Override
    public void receiveEditStrings() {
        BaseMod.loadCustomStringsFile(CardStrings.class, LOCALIZATION_DIR + "/CardStrings.json");
    }

    @Override
    public void receiveEditKeywords() {
        BaseMod.addKeyword(MOD_ID, "Consume", new String[] { "consume" },
                "When this card would be discarded from your hand, play it automatically and Exhaust it instead.",
                makeKeywordColor());
        BaseMod.addKeyword(MOD_ID, "Hoard", new String[] { "hoard" },
                "At the end of your turn, if this card is in your hand, it gains the listed damage and Block.",
                makeKeywordColor());
        BaseMod.addKeyword(MOD_ID, "Rot", new String[] { "rot" },
                "If this card would be discarded, or remains in your hand at end of turn, Exhaust it instead.",
                makeKeywordColor());
    }

    private KeywordColorInfo makeKeywordColor() {
        KeywordColorInfo info = new KeywordColorInfo();
        info.color = Settings.GOLD_COLOR.cpy();
        info.disableTooltipHeaderColor = false;
        return info;
    }
}
