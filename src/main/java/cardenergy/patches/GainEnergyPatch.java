package cardenergy.patches;

import cardenergy.util.HandFuelResourceAdapter;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "gainEnergy"
)
public class GainEnergyPatch {
    @SpirePrefixPatch
    public static SpireReturn<Void> Prefix(AbstractPlayer __instance, int e) {
        if (!HandFuelResourceAdapter.isActive(__instance)) {
            return SpireReturn.Continue();
        }
        HandFuelResourceAdapter.replaceEnergyGainWithDraw(__instance, e, "gainEnergy");
        return SpireReturn.Return(null);
    }
}
