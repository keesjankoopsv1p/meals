package nl.hu.bep3.kees.meals.core.application;

import nl.hu.bep3.kees.meals.core.application.query.GetIngredientsByMeal;
import nl.hu.bep3.kees.meals.core.domain.Meal;
import nl.hu.bep3.kees.meals.core.domain.exception.MealNotFoundException;
import nl.hu.bep3.kees.meals.core.port.storage.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MealQueryHandler {
    private final MealRepository mealRepository;

    public MealQueryHandler(MealRepository mealRepository) {
        this.mealRepository = mealRepository;
    }

    public List<String> handle(GetIngredientsByMeal query) {
        Meal meal = this.getMealById(query.getId());
        return meal.getIngredients().stream().toList();
    }

    private Meal getMealById(UUID id) {
        return this.mealRepository.findById(id)
                .orElseThrow(() -> new MealNotFoundException(id.toString()));
    }
}
