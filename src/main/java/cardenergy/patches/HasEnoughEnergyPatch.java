package cardenergy.patches;

import cardenergy.util.HandFuelResourceAdapter;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(
        clz = AbstractCard.class,
        method = "hasEnoughEnergy"
)
public class HasEnoughEnergyPatch {
    @SpirePrefixPatch
    public static SpireReturn<Boolean> Prefix(AbstractCard __instance) {
        if (!HandFuelResourceAdapter.isActivePlayer()) {
            return SpireReturn.Continue();
        }
        return SpireReturn.Return(HandFuelResourceAdapter.canPay(AbstractDungeon.player, __instance));
    }
}
