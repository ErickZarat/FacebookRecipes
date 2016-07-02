package org.erickzarat.android.facebookrecipes.recipemain;

import java.util.Random;

/**
 * Created by zarathos on 1/07/16
 */
public class GetNextRecipeInteractorImpl implements GetNextRecipeInteractor {
    private RecipeMainRepository repository;

    public GetNextRecipeInteractorImpl(RecipeMainRepository repository) {
        this.repository = repository;
    }

    @Override
    public void excecute() {
        int recipePage = new Random().nextInt(RecipeMainRepository.RECIPE_RANGE);
        repository.setRecipePage(recipePage);
        repository.getNextRecipe();
    }
}
