package cardenergy.patches;

import cardenergy.cards.rare.PreciousBauble;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

@SpirePatch(
        clz = AbstractCard.class,
        method = "canUse"
)
public class BlockAttackWhileBaubleInHandPatch {
    @SpirePrefixPatch
    public static SpireReturn<Boolean> Prefix(AbstractCard __instance, AbstractPlayer p, AbstractMonster m) {
        if (__instance.type != AbstractCard.CardType.ATTACK) {
            return SpireReturn.Continue();
        }
        if (!PreciousBauble.isInHand(p)) {
            return SpireReturn.Continue();
        }
        __instance.cantUseMessage = "The bauble holds your strike back.";
        return SpireReturn.Return(false);
    }
}
