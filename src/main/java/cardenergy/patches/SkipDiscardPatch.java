package cardenergy.patches;

import cardenergy.cards.IndigoCardHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

@SpirePatch(
        clz = CardGroup.class,
        method = "moveToDiscardPile"
)
public class SkipDiscardPatch {
    @SpirePrefixPatch
    public static SpireReturn<Void> Prefix(CardGroup __instance, AbstractCard c) {
        if (IndigoCardHelper.shouldSkipDiscard(c)) {
            return SpireReturn.Return(null);
        }
        return SpireReturn.Continue();
    }
}
