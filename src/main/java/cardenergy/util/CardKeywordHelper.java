package cardenergy.util;

import cardenergy.patches.CustomKeywordFieldPatch;
import cardenergy.patches.PendingDiscardReplacementFieldPatch;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public final class CardKeywordHelper {
    private static final int NO_DISCARD_REPLACEMENT = 0;
    private static final int CONSUME_DISCARD_REPLACEMENT = 1;
    private static final int EXHAUST_DISCARD_REPLACEMENT = 2;

    private CardKeywordHelper() {
    }

    public static void addKeyword(AbstractCard card, String keyword) {
        String normalized = keyword.toLowerCase();
        if (!card.keywords.contains(normalized)) {
            card.keywords.add(normalized);
        }
        card.initializeDescription();
    }

    public static void grantConsume(AbstractCard card) {
        if (card == null) {
            return;
        }
        CustomKeywordFieldPatch.hasConsume.set(card, true);
        addKeyword(card, "Consume");
    }

    public static void grantRot(AbstractCard card) {
        if (card == null) {
            return;
        }
        CustomKeywordFieldPatch.hasRot.set(card, true);
        addKeyword(card, "Rot");
    }

    public static void grantHoard(AbstractCard card, int damageGain, int blockGain, int magicGain) {
        if (card == null) {
            return;
        }
        setHoard(card, damageGain > 0, blockGain > 0, Math.max(damageGain, blockGain));
        addKeyword(card, "Hoard");
    }

    public static void setHoard(AbstractCard card, boolean growsDamage, boolean growsBlock, int amount) {
        if (card == null) {
            return;
        }
        CustomKeywordFieldPatch.hoardGrowsDamage.set(card, growsDamage);
        CustomKeywordFieldPatch.hoardGrowsBlock.set(card, growsBlock);
        card.baseMagicNumber = Math.max(0, amount);
        card.magicNumber = card.baseMagicNumber;
        card.selfRetain = true;
    }

    public static void grantHoardToBlock(AbstractCard card, int amount) {
        grantHoard(card, 0, amount, 0);
    }

    public static void grantHoardToDamageAndBlock(AbstractCard card, int amount) {
        grantHoard(card, amount, amount, 0);
    }

    public static void upgradeHoard(AbstractCard card, int damageDelta, int blockDelta, int magicDelta) {
        if (card == null || !hasHoard(card)) {
            return;
        }
        int amount = Math.max(Math.max(damageDelta, blockDelta), magicDelta);
        if (amount <= 0) {
            return;
        }
        card.baseMagicNumber += amount;
        card.magicNumber = card.baseMagicNumber;
        card.upgradedMagicNumber = true;
    }

    public static void upgradeHoardToBlock(AbstractCard card, int amount) {
        upgradeHoard(card, 0, amount, 0);
    }

    public static void upgradeHoardToDamageAndBlock(AbstractCard card, int amount) {
        upgradeHoard(card, amount, amount, 0);
    }

    public static boolean hasConsume(AbstractCard card) {
        return card != null && CustomKeywordFieldPatch.hasConsume.get(card);
    }

    public static boolean hasRot(AbstractCard card) {
        return card != null && CustomKeywordFieldPatch.hasRot.get(card);
    }

    public static boolean hasHoard(AbstractCard card) {
        return card != null
                && card.baseMagicNumber > 0
                && (CustomKeywordFieldPatch.hoardGrowsDamage.get(card)
                || CustomKeywordFieldPatch.hoardGrowsBlock.get(card));
    }

    public static boolean playsOnManualDiscard(AbstractCard card) {
        return hasConsume(card);
    }

    public static boolean exhaustsOnManualDiscard(AbstractCard card) {
        return !playsOnManualDiscard(card) && hasRot(card);
    }

    public static boolean exhaustsAtEndOfTurnInHand(AbstractCard card) {
        return hasRot(card) || (card != null && card.isEthereal);
    }

    public static boolean staysInHandAtEndOfTurn(AbstractCard card) {
        return card != null && (card.retain || card.selfRetain || hasHoard(card));
    }

    public static void handleManualDiscardKeywords(AbstractCard card) {
        if (card == null) {
            return;
        }
        if (playsOnManualDiscard(card)) {
            queueConsumeOnDiscard(card);
            return;
        }
        if (exhaustsOnManualDiscard(card)) {
            queueExhaustOnDiscard(card);
        }
    }

    public static void handleEndOfTurnKeywords(AbstractCard card) {
        if (card == null || AbstractDungeon.player == null || AbstractDungeon.player.hand == null) {
            return;
        }
        if (!AbstractDungeon.player.hand.group.contains(card)) {
            return;
        }

        if (hasRot(card)) {
            exhaustFromCurrentPile(card);
        }
    }

    public static void handleRetainedKeywords(AbstractCard card) {
        if (card == null) {
            return;
        }
        if (hasHoard(card)) {
            applyHoardGrowth(card);
        }
    }

    public static void applyHoardGrowth(AbstractCard card) {
        if (!hasHoard(card)) {
            return;
        }

        int amount = card.baseMagicNumber;

        if (CustomKeywordFieldPatch.hoardGrowsDamage.get(card)) {
            CustomKeywordFieldPatch.hoardDamageBonus.set(
                    card,
                    CustomKeywordFieldPatch.hoardDamageBonus.get(card) + amount
            );
        }
        if (CustomKeywordFieldPatch.hoardGrowsBlock.get(card)) {
            CustomKeywordFieldPatch.hoardBlockBonus.set(
                    card,
                    CustomKeywordFieldPatch.hoardBlockBonus.get(card) + amount
            );
        }

        card.applyPowers();
    }

    public static void resetHoard(AbstractCard card) {
        if (card == null || !hasHoard(card)) {
            return;
        }

        CustomKeywordFieldPatch.hoardDamageBonus.set(card, 0);
        CustomKeywordFieldPatch.hoardBlockBonus.set(card, 0);
        card.applyPowers();
    }

    public static int getHoardDamageBonus(AbstractCard card) {
        if (card == null || !hasHoard(card) || !CustomKeywordFieldPatch.hoardGrowsDamage.get(card)) {
            return 0;
        }
        return CustomKeywordFieldPatch.hoardDamageBonus.get(card);
    }

    public static int getHoardBlockBonus(AbstractCard card) {
        if (card == null || !hasHoard(card) || !CustomKeywordFieldPatch.hoardGrowsBlock.get(card)) {
            return 0;
        }
        return CustomKeywordFieldPatch.hoardBlockBonus.get(card);
    }

    public static int getEffectiveBaseDamage(AbstractCard card, int baseDamage) {
        return baseDamage + getHoardDamageBonus(card);
    }

    public static int getEffectiveBaseBlock(AbstractCard card, int baseBlock) {
        return baseBlock + getHoardBlockBonus(card);
    }

    public static void refreshHoardDisplayFlags(AbstractCard card) {
        if (card == null || !hasHoard(card)) {
            return;
        }

        if (getHoardDamageBonus(card) > 0) {
            card.isDamageModified = card.damage != card.baseDamage;
        }
        if (getHoardBlockBonus(card) > 0) {
            card.isBlockModified = card.block != card.baseBlock;
        }
    }

    public static void queueConsumeOnDiscard(AbstractCard card) {
        if (card != null) {
            queueConsumePlay(card);
            PendingDiscardReplacementFieldPatch.pendingDiscardReplacement.set(card, CONSUME_DISCARD_REPLACEMENT);
        }
    }

    public static void queueExhaustOnDiscard(AbstractCard card) {
        if (card != null) {
            PendingDiscardReplacementFieldPatch.pendingDiscardReplacement.set(card, EXHAUST_DISCARD_REPLACEMENT);
        }
    }

    public static void clearPendingDiscardReplacement(AbstractCard card) {
        if (card != null) {
            PendingDiscardReplacementFieldPatch.pendingDiscardReplacement.set(card, NO_DISCARD_REPLACEMENT);
        }
    }

    public static boolean replacePendingDiscard(CardGroup source, AbstractCard card) {
        if (source == null || card == null) {
            return false;
        }
        int replacement = PendingDiscardReplacementFieldPatch.pendingDiscardReplacement.get(card);
        if (replacement == NO_DISCARD_REPLACEMENT) {
            return false;
        }
        clearPendingDiscardReplacement(card);

        source.removeCard(card);
        CardGroup tempSource = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        tempSource.addToTop(card);
        AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, tempSource));
        return true;
    }

    private static void queueConsumePlay(AbstractCard card) {
        AbstractCard copy = card.makeStatEquivalentCopy();
        copy.freeToPlayOnce = true;
        copy.purgeOnUse = true;

        if (copy.target == AbstractCard.CardTarget.ENEMY || copy.target == AbstractCard.CardTarget.SELF_AND_ENEMY) {
            AbstractMonster target = AbstractDungeon.getRandomMonster();
            AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(copy, target, false, true));
        } else {
            AbstractDungeon.actionManager.addToBottom(new NewQueueCardAction(copy, false, true, true));
        }
    }

    public static void exhaustFromCurrentPile(AbstractCard card) {
        if (AbstractDungeon.player == null) {
            return;
        }
        if (AbstractDungeon.player.hand.group.contains(card)) {
            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
        } else if (AbstractDungeon.player.discardPile.group.contains(card)) {
            AbstractDungeon.actionManager.addToBottom(new ExhaustSpecificCardAction(card, AbstractDungeon.player.discardPile));
        } else if (AbstractDungeon.handCardSelectScreen != null
                && AbstractDungeon.handCardSelectScreen.selectedCards.group.contains(card)) {
            AbstractDungeon.actionManager.addToBottom(
                    new ExhaustSpecificCardAction(card, AbstractDungeon.handCardSelectScreen.selectedCards)
            );
        }
    }
}
