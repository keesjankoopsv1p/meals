package nl.hu.bep3.kees.meals.infrastructure.driver.web;

import nl.hu.bep3.kees.meals.core.application.MealCommandHandler;
import nl.hu.bep3.kees.meals.core.application.command.AddNewMeal;
import nl.hu.bep3.kees.meals.core.domain.Meal;
import nl.hu.bep3.kees.meals.infrastructure.driver.web.request.AddNewMealRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/meals")
public class MealCommandController {
    private final MealCommandHandler commandHandler;

    public MealCommandController(MealCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public Meal addNewMeal(@Valid @RequestBody AddNewMealRequest request) {
        return this.commandHandler.handle(
                new AddNewMeal(request.name, request.price, request.ingredients)
        );
    }
}
