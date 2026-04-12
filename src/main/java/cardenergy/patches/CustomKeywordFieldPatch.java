package cardenergy.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpireField;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;

@SpirePatch(
        clz = AbstractCard.class,
        method = SpirePatch.CLASS
)
public class CustomKeywordFieldPatch {
    public static final SpireField<Boolean> hasConsume = new SpireField<>(() -> false);
    public static final SpireField<Boolean> hasRot = new SpireField<>(() -> false);
    public static final SpireField<Boolean> hoardGrowsDamage = new SpireField<>(() -> false);
    public static final SpireField<Boolean> hoardGrowsBlock = new SpireField<>(() -> false);
    public static final SpireField<Integer> hoardDamageBonus = new SpireField<>(() -> 0);
    public static final SpireField<Integer> hoardBlockBonus = new SpireField<>(() -> 0);

    private CustomKeywordFieldPatch() {
    }
}
