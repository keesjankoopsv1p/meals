package nl.hu.bep3.kees.meals.infrastructure.driver.web;

import nl.hu.bep3.kees.meals.core.application.MealQueryHandler;
import nl.hu.bep3.kees.meals.core.application.query.GetIngredientsByMeal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/meals")
public class MealQueryController {
    private final MealQueryHandler queryHandler;

    public MealQueryController(MealQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping("/{id}/ingredients")
    public List<String> getIngredientsByMeal(@PathVariable UUID id){
        return queryHandler.handle(new GetIngredientsByMeal(id));
    }
}
