package cardenergy.patches;

import cardenergy.util.HandFuelResourceAdapter;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

@SpirePatch(
        clz = EnergyPanel.class,
        method = "render"
)
public class EnergyPanelPatch {
    @SpirePrefixPatch
    public static SpireReturn<Void> Prefix(EnergyPanel __instance) {
        if (!HandFuelResourceAdapter.isActivePlayer()) {
            return SpireReturn.Continue();
        }
        return SpireReturn.Return(null);
    }
}
