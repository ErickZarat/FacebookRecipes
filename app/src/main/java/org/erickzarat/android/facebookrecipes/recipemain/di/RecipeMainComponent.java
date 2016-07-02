package org.erickzarat.android.facebookrecipes.recipemain.di;

import dagger.Component;
import org.erickzarat.android.facebookrecipes.libs.base.ImageLoader;
import org.erickzarat.android.facebookrecipes.libs.di.LibsModule;
import org.erickzarat.android.facebookrecipes.recipemain.RecipeMainPresenter;
import org.erickzarat.android.facebookrecipes.recipemain.ui.RecipeMainActivity;

import javax.inject.Singleton;

/**
 * Created by zarathos on 1/07/16
 */
@Singleton
@Component(modules = {RecipeMainModule.class, LibsModule.class})
public interface RecipeMainComponent {
    //void inject(RecipeMainActivity activity);
    ImageLoader getImageLoader();
    RecipeMainPresenter getPresenter();
}
