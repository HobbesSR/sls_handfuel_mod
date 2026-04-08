package cardenergy.patches;

import cardenergy.util.HandFuelResourceAdapter;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(
        clz = AbstractCard.class,
        method = "getCost"
)
public class GetCostPatch {
    @SpirePrefixPatch
    public static SpireReturn<String> Prefix(AbstractCard __instance) {
        if (!HandFuelResourceAdapter.isActivePlayer()) {
            return SpireReturn.Continue();
        }
        return SpireReturn.Return(HandFuelResourceAdapter.getDisplayedCost(__instance));
    }
}
