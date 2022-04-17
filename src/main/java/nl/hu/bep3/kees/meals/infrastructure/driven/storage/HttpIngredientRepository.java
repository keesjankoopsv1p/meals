package nl.hu.bep3.kees.meals.infrastructure.driven.storage;

import nl.hu.bep3.kees.meals.core.port.storage.IngredientRepository;
import nl.hu.bep3.kees.meals.infrastructure.driven.storage.result.IngredientResult;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class HttpIngredientRepository implements IngredientRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpIngredientRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    @Override
    public IngredientResult getIngredientById(String id) {
        URI uri = URI.create(this.rootPath + "/ingredients/" + id);
        IngredientResult result = this.client.getForObject(uri, IngredientResult.class);
        return result;
    }
}
