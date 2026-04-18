package cardenergy.powers;

import cardenergy.CardEnergyMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class CounterthrowPower extends AbstractPower {
    public static final String POWER_ID = CardEnergyMod.makeID("Counterthrow");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CounterthrowPower(AbstractCreature owner, int amount) {
        this.name = NAME;
        this.ID = POWER_ID;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.BUFF;
        // Placeholder icon reuses the vanilla Thorns icon until custom Counterthrow art is produced.
        loadRegion("thorns");
        updateDescription();
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS
                && info.type != DamageInfo.DamageType.HP_LOSS
                && info.owner != null
                && info.owner != this.owner
                && this.amount > 0) {
            int retaliate = this.owner.currentBlock;
            if (retaliate > 0) {
                AbstractDungeon.actionManager.addToBottom(new DamageAction(
                        info.owner,
                        new DamageInfo(this.owner, retaliate, DamageInfo.DamageType.THORNS),
                        AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
            this.amount--;
            if (this.amount <= 0) {
                AbstractDungeon.actionManager.addToBottom(
                        new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            } else {
                updateDescription();
            }
        }
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        if (this.amount > 0) {
            AbstractDungeon.actionManager.addToBottom(
                    new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }
}
