package nl.hu.bep3.kees.meals.core.port.storage;

import nl.hu.bep3.kees.meals.infrastructure.driven.storage.result.IngredientResult;

public interface IngredientRepository {
    IngredientResult getIngredientById(String id);
}
