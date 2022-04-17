package nl.hu.bep3.kees.meals.core.port.storage;

import nl.hu.bep3.kees.meals.core.domain.Meal;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MealRepository extends MongoRepository<Meal, UUID> {

    //Find al meals that containt the given ingredient
    @Query(value = "{ 'ingredients' : ?0 }")
    List<Meal> findMealsByIngredient(String ingerdientId);
}
