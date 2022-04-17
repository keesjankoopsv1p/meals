package nl.hu.bep3.kees.meals.infrastructure.driver.web.request;

import javax.validation.constraints.NotBlank;
import java.util.Set;

public class AddNewMealRequest {
    @NotBlank
    public String name;
    public Integer price;
    public Set<String> ingredients;

}
