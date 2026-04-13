package cardenergy.cards;

import basemod.abstracts.CustomCard;
import cardenergy.character.CardEnergyCharacterEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;

public abstract class AbstractTerracottaCard extends CustomCard {
    protected AbstractTerracottaCard(
            String id,
            AbstractCard.CardType type,
            AbstractCard.CardRarity rarity,
            AbstractCard.CardTarget target,
            int cost
    ) {
        super(
                id,
                "",
                (String) null,
                cost,
                "",
                type,
                CardEnergyCharacterEnum.TERRACOTTA,
                rarity,
                target
        );
    }
}
