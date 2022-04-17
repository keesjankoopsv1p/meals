package nl.hu.bep3.kees.meals.core.application.query;

import java.util.UUID;

public class GetIngredientsByMeal {
    private final UUID id;

    public GetIngredientsByMeal(UUID id) { this.id = id; }

    public UUID getId() { return this.id; }
}
