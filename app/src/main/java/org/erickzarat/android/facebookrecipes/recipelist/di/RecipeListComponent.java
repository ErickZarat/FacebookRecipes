package org.erickzarat.android.facebookrecipes.recipelist.di;

import dagger.Component;
import org.erickzarat.android.facebookrecipes.libs.di.LibsModule;
import org.erickzarat.android.facebookrecipes.recipelist.RecipeListPresenter;
import org.erickzarat.android.facebookrecipes.recipelist.ui.adapters.RecipesAdapter;

import javax.inject.Singleton;

/**
 * Created by zarathos on 2/07/16
 */
@Singleton
@Component(modules = {RecipeListModule.class, LibsModule.class})
public interface RecipeListComponent {
    RecipeListPresenter getPresenter();
    RecipesAdapter getAdapter();
}
