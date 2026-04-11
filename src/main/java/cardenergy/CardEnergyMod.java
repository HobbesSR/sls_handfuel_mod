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
import cardenergy.character.CardEnergyCharacter;
import cardenergy.character.CardEnergyCharacterEnum;
import cardenergy.character.IndigoColorScheme;
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
        IndigoColorScheme.register();
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
                CardEnergyCharacterEnum.HAND_FUEL_INDIGO
        );
        logger.info("Registered hand-fuel indigo test character");
    }

    @Override
    public void receiveEditCards() {
        RedMirrorCards.registerAll();
        BaseMod.addCard(new ScroungeStrike());
        BaseMod.addCard(new ScroungeDefend());
        BaseMod.addCard(new Brace());
        BaseMod.addCard(new ScrapBurst());
        BaseMod.addCard(new RottingBlow());
        BaseMod.addCard(new Recovery());
        BaseMod.addCard(new RottingShelter());
        BaseMod.addCard(new Stockpile());
        BaseMod.addCard(new ScrapSpray());
        BaseMod.addCard(new RottingSlash());
        BaseMod.addCard(new SalvageSwing());
        BaseMod.addCard(new PileDriver());
        BaseMod.addCard(new BraceForImpact());
        BaseMod.addCard(new GuardTheHeap());
        BaseMod.addCard(new Hunker());
        BaseMod.addCard(new DugIn());
        BaseMod.addCard(new ScrapKnife());
        BaseMod.addCard(new BurnThrough());
        BaseMod.addCard(new LooseParts());
        BaseMod.addCard(new AshenCount());
        BaseMod.addCard(new Patchwork());
        BaseMod.addCard(new BarbedGuard());
        BaseMod.addCard(new TurnAside());
        BaseMod.addCard(new HiddenCache());
        BaseMod.addCard(new ScavengeTheWreck());
        BaseMod.addCard(new PackedSwing());
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
