package org.erickzarat.android.facebookrecipes.recipemain.di;

import dagger.Module;
import dagger.Provides;
import org.erickzarat.android.facebookrecipes.api.RecipeClient;
import org.erickzarat.android.facebookrecipes.api.RecipeService;
import org.erickzarat.android.facebookrecipes.libs.base.EventBus;
import org.erickzarat.android.facebookrecipes.recipemain.*;
import org.erickzarat.android.facebookrecipes.recipemain.ui.RecipeMainView;

import javax.inject.Singleton;

/**
 * Created by zarathos on 1/07/16
 */
@Module
public class RecipeMainModule {
    RecipeMainView view;

    public RecipeMainModule(RecipeMainView view) {
        this.view = view;
    }

    @Provides @Singleton
    RecipeMainView providesRecipeMainView(){
        return  this.view;
    }

    @Provides @Singleton
    RecipeMainPresenter providesRecipeMainPresenter(RecipeMainView view, EventBus eventBus, SaveRecipeInteractor saveInteractor, GetNextRecipeInteractor getNextInteractor) {
        return new RecipeMainPresenterImpl(view, eventBus, saveInteractor, getNextInteractor);
    }

    @Provides @Singleton
    SaveRecipeInteractor providesSaveRecipeInteractor(RecipeMainRepository repository){
        return new SaveRecipeInteractorImpl(repository);
    }

    @Provides @Singleton
    GetNextRecipeInteractor providesGetNextRecipeInteractor(RecipeMainRepository repository){
        return new GetNextRecipeInteractorImpl(repository);
    }

    @Provides @Singleton
    RecipeMainRepository providesRecipeMainRepository( EventBus eventBus, RecipeService service){
        return new RecipeMainRepositoryImpl(eventBus, service);
    }

    @Provides @Singleton
    RecipeService providesRecipeService(){
        return new RecipeClient().getRecipeService();
    }
}
