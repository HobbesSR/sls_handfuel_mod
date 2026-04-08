package cardenergy.patches;

import basemod.ReflectionHacks;
import cardenergy.character.CardEnergyCharacterEnum;
import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.neow.NeowEvent;
import com.megacrit.cardcrawl.neow.NeowReward;

import java.util.ArrayList;

@SpirePatch(
        clz = NeowEvent.class,
        method = SpirePatch.CONSTRUCTOR,
        paramtypez = {boolean.class}
)
public class NeowRewardPatch {
    @SpirePostfixPatch
    public static void Postfix(NeowEvent __instance, boolean isDone) {
        if (isDone || AbstractDungeon.player == null || AbstractDungeon.player.chosenClass != CardEnergyCharacterEnum.HAND_FUEL_INDIGO) {
            return;
        }

        ArrayList<NeowReward> rewards = new ArrayList<>();
        rewards.add(createRandomBlessing());
        rewards.add(createDeckShapingBlessing());

        ReflectionHacks.setPrivate(__instance, NeowEvent.class, "rewards", rewards);
        __instance.imageEventText.updateDialogOption(0, rewards.get(0).optionLabel);
        __instance.imageEventText.updateDialogOption(1, rewards.get(1).optionLabel);
    }

    private static NeowReward createRandomBlessing() {
        NeowReward reward = new NeowReward(false);
        switch (MathUtils.random(2)) {
            case 0:
                reward.type = NeowReward.NeowRewardType.UPGRADE_CARD;
                reward.optionLabel = "[ Upgrade a card ]";
                break;
            case 1:
                reward.type = NeowReward.NeowRewardType.RANDOM_COMMON_RELIC;
                reward.optionLabel = "[ Obtain a random Common Relic ]";
                break;
            default:
                reward.type = NeowReward.NeowRewardType.HUNDRED_GOLD;
                reward.optionLabel = "[ Gain 100 Gold ]";
                break;
        }
        reward.drawback = NeowReward.NeowRewardDrawback.NONE;
        return reward;
    }

    private static NeowReward createDeckShapingBlessing() {
        NeowReward reward = new NeowReward(false);
        reward.type = NeowReward.NeowRewardType.REMOVE_CARD;
        reward.drawback = NeowReward.NeowRewardDrawback.NONE;
        reward.optionLabel = "[ Remove a card from your deck ]";
        return reward;
    }
}
