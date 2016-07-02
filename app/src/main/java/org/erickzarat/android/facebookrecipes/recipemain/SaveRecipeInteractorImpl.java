package org.erickzarat.android.facebookrecipes.recipemain;

import org.erickzarat.android.facebookrecipes.entities.Recipe;

/**
 * Created by zarathos on 1/07/16
 */
public class SaveRecipeInteractorImpl implements SaveRecipeInteractor {
    RecipeMainRepository repository;

    public SaveRecipeInteractorImpl(RecipeMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void excecute(Recipe recipe) {
        repository.saveRecipe(recipe);
    }
}
