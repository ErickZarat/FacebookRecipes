package org.erickzarat.android.facebookrecipes.recipelist;

import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.Select;
import org.erickzarat.android.facebookrecipes.entities.Recipe;
import org.erickzarat.android.facebookrecipes.entities.Recipe_Table;
import org.erickzarat.android.facebookrecipes.libs.base.EventBus;
import org.erickzarat.android.facebookrecipes.recipelist.events.RecipeListEvent;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zarathos on 2/07/16
 */
public class RecipeListRepositoryImpl implements RecipeListRepository {
    private EventBus eventBus;

    public RecipeListRepositoryImpl(EventBus eventBus) {
        this.eventBus = eventBus;
    }

    @Override
    public void getSavedRecipes() {
        FlowCursorList<Recipe> storedRecipes = new FlowCursorList<Recipe>(false, Recipe.class);
        post(RecipeListEvent.READ_EVENT, storedRecipes.getAll());
        storedRecipes.close();
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipe.update();
        post();
    }

    @Override
    public void removeRecipe(Recipe recipe) {
        recipe.delete();
        post(RecipeListEvent.DELETE_EVENT, Arrays.asList(recipe));
    }

    @Override
    public void getFavoritesRecipes() {
        List<Recipe> recipes = new Select().from(Recipe.class).where(Recipe_Table.favorite.is(true)).queryList();
        post(RecipeListEvent.READ_EVENT, recipes);
    }

    private void post(int type, List<Recipe> recipes) {
        RecipeListEvent event = new RecipeListEvent();
        event.setRecipes(recipes);
        event.setType(type);
        eventBus.post(event);
    }

    private void post() {
        post(RecipeListEvent.UPDATE_EVENT, null);
    }

}