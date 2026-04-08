package cardenergy;

import basemod.BaseMod;
import basemod.interfaces.EditCharactersSubscriber;
import cardenergy.character.CardEnergyCharacter;
import cardenergy.character.CardEnergyCharacterEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SpireInitializer
public class CardEnergyMod implements EditCharactersSubscriber {
    public static final Logger logger = LogManager.getLogger(CardEnergyMod.class.getName());

    public static final String MOD_ID = "cardenergy";
    public static final String IMG_DIR = "img/cardenergy";
    public static final String BUTTON_IMAGE = IMG_DIR + "/charSelect/handfuelButton.png";
    public static final String PORTRAIT_IMAGE = IMG_DIR + "/charSelect/handfuelPortrait.jpg";

    public CardEnergyMod() {
        BaseMod.subscribe(this);
    }

    public static void initialize() {
        new CardEnergyMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(
                new CardEnergyCharacter(CardCrawlGame.playerName),
                BUTTON_IMAGE,
                PORTRAIT_IMAGE,
                CardEnergyCharacterEnum.HAND_FUEL_RED
        );
        logger.info("Registered hand-fuel red test character");
    }
}
