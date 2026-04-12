package cardenergy.character;

import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.helpers.CardLibrary;

public class CardEnergyCharacterEnum {
    @SpireEnum
    public static AbstractPlayer.PlayerClass HAND_FUEL_TERRACOTTA;

    @SpireEnum(name = "CARDENERGY_TERRACOTTA")
    public static AbstractCard.CardColor TERRACOTTA;

    @SpireEnum(name = "CARDENERGY_TERRACOTTA")
    public static CardLibrary.LibraryType LIBRARY_TERRACOTTA;
}
