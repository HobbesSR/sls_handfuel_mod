package cardenergy.patches;

import cardenergy.util.CardKeywordHelper;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import javassist.CannotCompileException;
import javassist.expr.ExprEditor;
import javassist.expr.FieldAccess;

public class HoardStatPatch {
    private HoardStatPatch() {
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "applyPowers"
    )
    public static class ApplyPowersPatch {
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(FieldAccess fieldAccess) throws CannotCompileException {
                    if (fieldAccess.isReader()
                            && fieldAccess.getClassName().equals(AbstractCard.class.getName())
                            && fieldAccess.getFieldName().equals("baseDamage")) {
                        String helper = CardKeywordHelper.class.getName();
                        fieldAccess.replace("{ $_ = " + helper + ".getEffectiveBaseDamage($0, $proceed()); }");
                    }
                }
            };
        }

        @SpirePostfixPatch
        public static void Postfix(AbstractCard __instance) {
            CardKeywordHelper.refreshHoardDisplayFlags(__instance);
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "applyPowersToBlock"
    )
    public static class ApplyPowersToBlockPatch {
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(FieldAccess fieldAccess) throws CannotCompileException {
                    if (fieldAccess.isReader()
                            && fieldAccess.getClassName().equals(AbstractCard.class.getName())
                            && fieldAccess.getFieldName().equals("baseBlock")) {
                        String helper = CardKeywordHelper.class.getName();
                        fieldAccess.replace("{ $_ = " + helper + ".getEffectiveBaseBlock($0, $proceed()); }");
                    }
                }
            };
        }

        @SpirePostfixPatch
        public static void Postfix(AbstractCard __instance) {
            CardKeywordHelper.refreshHoardDisplayFlags(__instance);
        }
    }

    @SpirePatch(
            clz = AbstractCard.class,
            method = "calculateCardDamage"
    )
    public static class CalculateCardDamagePatch {
        public static ExprEditor Instrument() {
            return new ExprEditor() {
                @Override
                public void edit(FieldAccess fieldAccess) throws CannotCompileException {
                    if (fieldAccess.isReader()
                            && fieldAccess.getClassName().equals(AbstractCard.class.getName())
                            && fieldAccess.getFieldName().equals("baseDamage")) {
                        String helper = CardKeywordHelper.class.getName();
                        fieldAccess.replace("{ $_ = " + helper + ".getEffectiveBaseDamage($0, $proceed()); }");
                    }
                }
            };
        }

        @SpirePostfixPatch
        public static void Postfix(AbstractCard __instance, AbstractMonster mo) {
            CardKeywordHelper.refreshHoardDisplayFlags(__instance);
        }
    }
}
