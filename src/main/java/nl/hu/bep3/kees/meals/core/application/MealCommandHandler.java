package nl.hu.bep3.kees.meals.core.application;

import nl.hu.bep3.kees.meals.core.application.command.AddNewMeal;
import nl.hu.bep3.kees.meals.core.application.command.RemoveMealsFromMenuByIngredient;
import nl.hu.bep3.kees.meals.core.domain.Meal;
import nl.hu.bep3.kees.meals.core.domain.event.MealEvent;
import nl.hu.bep3.kees.meals.core.port.messaging.MealEventPublisher;
import nl.hu.bep3.kees.meals.core.port.storage.IngredientRepository;
import nl.hu.bep3.kees.meals.core.port.storage.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealCommandHandler {
    private final MealEventPublisher eventPublisher;
    private final MealRepository mealRepository;
    private final IngredientRepository ingredientRepository;

    public MealCommandHandler(MealRepository mealRepository, MealEventPublisher eventPublisher, IngredientRepository ingredientRepository) {

        this.eventPublisher = eventPublisher;
        this.mealRepository = mealRepository;
        this.ingredientRepository = ingredientRepository;
    }

    //save a new meal and add it to the menu according to the ingredients in stock
    public Meal handle(AddNewMeal command) {
        Meal newMeal = new Meal(command.getName(), command.getPrice(), command.getIngredients());

        // Determine if meal should be put on the menu according to ingredients in stock
        if (checkIfIngredientsInStock(newMeal.getIngredients().stream().toList())){
            newMeal.setOnMenu();
        }

        this.mealRepository.save(newMeal);
        newMeal.registerMeal();
        publishEventsFor(newMeal);
        return newMeal;
    }

    //Take all the meals of the menu that contain a certain ingredient
    public List<Meal> handle(RemoveMealsFromMenuByIngredient command) {
        List<Meal> mealsWithIngredient = this.mealRepository.findMealsByIngredient(command.getIngredient());
        for (Meal meal: mealsWithIngredient) {
            meal.takeOffMenu();
        }
        this.mealRepository.saveAll(mealsWithIngredient);
        return mealsWithIngredient;
    }


    //private methods
    private void publishEventsFor(Meal meal) {
        List<MealEvent> events = meal.listEvents();
        events.forEach(eventPublisher::publish);
        meal.clearEvents();
    }

    // Checks whether all ingredients are in stock. returns True if all ingredients are in stock.
    private boolean checkIfIngredientsInStock(List<String> ingredients){
        boolean allInStock = true;
        for (String ingredient: ingredients) {
            Integer quantity = ingredientRepository.getIngredientById(ingredient).quantity;
            if (quantity == 0) {
                allInStock = false;
            }
        }
        return allInStock;
    }
}
