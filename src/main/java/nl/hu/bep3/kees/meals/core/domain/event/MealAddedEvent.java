package nl.hu.bep3.kees.meals.core.domain.event;

import java.util.UUID;

public class MealAddedEvent extends MealEvent {
    private final UUID meal;

    public MealAddedEvent(UUID id) {
        this.meal = id;
    }

    @Override
    public String getEventKey() { return "meals.stock.added"; }

    public UUID getMeal() {
        return meal;
    }
}
