package org.erickzarat.android.facebookrecipes.recipelist;

import org.erickzarat.android.facebookrecipes.entities.Recipe;

/**
 * Created by zarathos on 1/07/16
 */
public interface RecipeListRepository {
    void getSavedRecipes();
    void updateRecipe(Recipe recipe);
    void removeRecipe(Recipe recipe);
    void getFavoritesRecipes();
}
