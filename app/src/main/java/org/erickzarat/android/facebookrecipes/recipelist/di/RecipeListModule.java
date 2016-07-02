package org.erickzarat.android.facebookrecipes.recipelist.di;

import dagger.Module;
import dagger.Provides;
import org.erickzarat.android.facebookrecipes.entities.Recipe;
import org.erickzarat.android.facebookrecipes.libs.base.EventBus;
import org.erickzarat.android.facebookrecipes.libs.base.ImageLoader;
import org.erickzarat.android.facebookrecipes.recipelist.*;
import org.erickzarat.android.facebookrecipes.recipelist.ui.RecipeListView;
import org.erickzarat.android.facebookrecipes.recipelist.ui.adapters.OnItemClickListener;
import org.erickzarat.android.facebookrecipes.recipelist.ui.adapters.RecipesAdapter;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zarathos on 2/07/16
 */
@Module
public class RecipeListModule {
    RecipeListView view;
    OnItemClickListener onItemClickListener;

    public RecipeListModule(RecipeListView view, OnItemClickListener onItemClickListener) {
        this.view = view;
        this.onItemClickListener = onItemClickListener;
    }

    @Provides
    @Singleton
    RecipeListView provideRecipeListView() {
        return this.view;
    }

    @Provides @Singleton
    RecipeListPresenter provideRecipeListPresenter(EventBus eventBus, RecipeListView view, RecipeListInteractor listInteractor, StoredRecipesInteractor storedInteractor) {
        return new RecipeListPresenterImpl(eventBus, view, listInteractor, storedInteractor);
    }

    @Provides @Singleton
    RecipeListInteractor provideRecipeListInteractor(RecipeListRepository repository) {
        return new RecipeListInteractorImpl(repository);
    }

    @Provides @Singleton
    StoredRecipesInteractor provideStoredRecipesInteractor(RecipeListRepository repository) {
        return new StoredRecipesInteractorImpl(repository);
    }

    @Provides @Singleton
    RecipeListRepository provideRecipeListRepository(EventBus eventBus) {
        return new RecipeListRepositoryImpl(eventBus);
    }

    @Provides @Singleton
    RecipesAdapter provideRecipesAdapter(List<Recipe> recipes, ImageLoader imageLoader, OnItemClickListener onItemClickListener) {
        return new RecipesAdapter(recipes, imageLoader, onItemClickListener);
    }

    @Provides @Singleton
    OnItemClickListener provideOnItemClickListener() {
        return this.onItemClickListener;
    }

    @Provides @Singleton
    List<Recipe> provideEmptyList() {
        return new ArrayList<Recipe>();
    }
}
