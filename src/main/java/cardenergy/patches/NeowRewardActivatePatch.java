package cardenergy.patches;

import basemod.ReflectionHacks;
import cardenergy.character.CardEnergyCharacterEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.neow.NeowReward;
import com.megacrit.cardcrawl.relics.AbstractRelic;

@SpirePatch(
        clz = NeowReward.class,
        method = "activate"
)
public class NeowRewardActivatePatch {
    @SpirePrefixPatch
    public static SpireReturn<Void> Prefix(NeowReward __instance) {
        if (AbstractDungeon.player == null || AbstractDungeon.player.chosenClass != CardEnergyCharacterEnum.HAND_FUEL_TERRACOTTA) {
            return SpireReturn.Continue();
        }
        if (__instance.drawback != NeowReward.NeowRewardDrawback.NONE) {
            return SpireReturn.Continue();
        }

        switch (__instance.type) {
            case UPGRADE_CARD:
                ReflectionHacks.setPrivate(__instance, NeowReward.class, "activated", true);
                AbstractDungeon.gridSelectScreen.open(
                        AbstractDungeon.player.masterDeck.getUpgradableCards(),
                        1,
                        NeowReward.TEXT[27],
                        true,
                        false,
                        false,
                        false
                );
                return SpireReturn.Return(null);
            case REMOVE_CARD:
                ReflectionHacks.setPrivate(__instance, NeowReward.class, "activated", true);
                AbstractDungeon.gridSelectScreen.open(
                        AbstractDungeon.player.masterDeck.getPurgeableCards(),
                        1,
                        NeowReward.TEXT[25],
                        false,
                        true,
                        false,
                        false
                );
                return SpireReturn.Return(null);
            case HUNDRED_GOLD:
                CardCrawlGame.sound.play("GOLD_JINGLE");
                AbstractDungeon.player.gainGold(100);
                return SpireReturn.Return(null);
            case RANDOM_COMMON_RELIC:
                AbstractDungeon.getCurrRoom().spawnRelicAndObtain(
                        Settings.WIDTH / 2.0F,
                        Settings.HEIGHT / 2.0F,
                        AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.COMMON)
                );
                return SpireReturn.Return(null);
            default:
                return SpireReturn.Continue();
        }
    }
}

