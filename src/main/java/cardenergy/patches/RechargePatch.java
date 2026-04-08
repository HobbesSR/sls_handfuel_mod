package cardenergy.patches;

import cardenergy.util.HandFuelResourceAdapter;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(
        clz = EnergyManager.class,
        method = "recharge"
)
public class RechargePatch {
    @SpirePrefixPatch
    public static SpireReturn<Void> Prefix(EnergyManager __instance) {
        if (!HandFuelResourceAdapter.isActivePlayer()) {
            return SpireReturn.Continue();
        }
        HandFuelResourceAdapter.replaceEnergyGainWithDraw(
                AbstractDungeon.player,
                HandFuelResourceAdapter.getRechargeAmount(__instance),
                "recharge"
        );
        return SpireReturn.Return(null);
    }
}
