package org.erickzarat.android.facebookrecipes.recipelist.ui;

import org.erickzarat.android.facebookrecipes.entities.Recipe;

import java.util.List;

/**
 * Created by zarathos on 1/07/16
 */
public interface RecipeListView {
    void setRecipes(List<Recipe> data);
    void recipeUpdated();
    void recipeDeleted(Recipe recipe);
}
