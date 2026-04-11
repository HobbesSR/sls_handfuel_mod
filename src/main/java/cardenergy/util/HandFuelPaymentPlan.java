package cardenergy.util;

import com.megacrit.cardcrawl.cards.AbstractCard;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class HandFuelPaymentPlan {
    private final List<AbstractCard> fuelCards;
    private final int resolvedEnergyOnUse;

    private HandFuelPaymentPlan(List<AbstractCard> fuelCards, int resolvedEnergyOnUse) {
        this.fuelCards = Collections.unmodifiableList(new ArrayList<>(fuelCards));
        this.resolvedEnergyOnUse = resolvedEnergyOnUse;
    }

    public static HandFuelPaymentPlan forFixedCost(List<AbstractCard> fuelCards, int energyOnUse) {
        return new HandFuelPaymentPlan(fuelCards, energyOnUse);
    }

    public static HandFuelPaymentPlan forXCost(List<AbstractCard> fuelCards, int xValue) {
        return new HandFuelPaymentPlan(fuelCards, xValue);
    }

    public List<AbstractCard> getFuelCards() {
        return fuelCards;
    }

    public int getResolvedEnergyOnUse() {
        return resolvedEnergyOnUse;
    }
}
