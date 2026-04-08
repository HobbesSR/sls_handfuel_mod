package cardenergy.patches;

import cardenergy.util.HandFuelResourceAdapter;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.MethodCall;

@SpirePatch(
        clz = AbstractPlayer.class,
        method = "useCard"
)
public class UseCardPatch {
    @SpirePrefixPatch
    public static SpireReturn<Void> Prefix(AbstractPlayer __instance, AbstractCard c, AbstractMonster monster, int energyOnUse) {
        if (!HandFuelResourceAdapter.shouldInterceptCardUse(__instance, c)) {
            return SpireReturn.Continue();
        }
        HandFuelResourceAdapter.queueFuelSelection(__instance, c, monster, energyOnUse);
        return SpireReturn.Return(null);
    }

    public static ExprEditor Instrument() {
        return new ExprEditor() {
            @Override
            public void edit(MethodCall methodCall) throws CannotCompileException {
                if (methodCall.getClassName().equals(EnergyManager.class.getName())
                        && methodCall.getMethodName().equals("use")) {
                    String adapter = HandFuelResourceAdapter.class.getName();
                    methodCall.replace(
                            "if (!" + adapter + ".shouldSuppressEnergySpend(this, c)) {" +
                                    "$proceed($$);" +
                            "}"
                    );
                }
            }
        };
    }
}
