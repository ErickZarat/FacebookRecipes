package org.erickzarat.android.facebookrecipes.recipemain;

import org.erickzarat.android.facebookrecipes.entities.Recipe;
import org.erickzarat.android.facebookrecipes.recipemain.events.RecipeMainEvent;
import org.erickzarat.android.facebookrecipes.recipemain.ui.RecipeMainView;

/**
 * Created by zarathos on 1/07/16
 */
public interface RecipeMainPresenter {
    void onCreate();
    void onDestroy();

    void dismissRecipe();
    void getNextRecipe();
    void saveRecipe(Recipe recipe);
    void onEventMainThread(RecipeMainEvent event);

    void imageReady();
    void imageError(String error);
    RecipeMainView getView();
}
