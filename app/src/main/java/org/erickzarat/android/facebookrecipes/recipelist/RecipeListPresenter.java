package org.erickzarat.android.facebookrecipes.recipelist;

import org.erickzarat.android.facebookrecipes.entities.Recipe;
import org.erickzarat.android.facebookrecipes.recipelist.events.RecipeListEvent;
import org.erickzarat.android.facebookrecipes.recipelist.ui.RecipeListView;

/**
 * Created by zarathos on 1/07/16
 */
public interface RecipeListPresenter {
    void onCreate();
    void onDestroy();

    void getRecipes();
    void removeRecipe(Recipe recipe);
    void toggleFavorite(Recipe recipe);
    void onEventMainThread(RecipeListEvent event);

    void showAll();
    void showFavs();

    RecipeListView getView();
}
