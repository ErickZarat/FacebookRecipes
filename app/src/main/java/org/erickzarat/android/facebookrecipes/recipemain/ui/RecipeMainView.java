package org.erickzarat.android.facebookrecipes.recipemain.ui;

import org.erickzarat.android.facebookrecipes.entities.Recipe;

/**
 * Created by zarathos on 1/07/16
 */
public interface RecipeMainView {
    void showProgress();
    void hideProgress();
    void showUIElements();
    void hideUIElements();
    void saveAnimation();
    void dismissAnimation();

    void onRecipeSaved();

    void setRecipe(Recipe recipe);
    void onGetRecipeError(String error);
}
