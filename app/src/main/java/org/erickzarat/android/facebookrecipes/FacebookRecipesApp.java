package org.erickzarat.android.facebookrecipes;

import android.app.Application;
import android.content.Intent;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.raizlabs.android.dbflow.config.FlowManager;
import org.erickzarat.android.facebookrecipes.libs.di.LibsModule;
import org.erickzarat.android.facebookrecipes.login.ui.LoginActivity;
import org.erickzarat.android.facebookrecipes.recipelist.di.DaggerRecipeListComponent;
import org.erickzarat.android.facebookrecipes.recipelist.di.RecipeListComponent;
import org.erickzarat.android.facebookrecipes.recipelist.di.RecipeListModule;
import org.erickzarat.android.facebookrecipes.recipelist.ui.RecipeListActivity;
import org.erickzarat.android.facebookrecipes.recipelist.ui.RecipeListView;
import org.erickzarat.android.facebookrecipes.recipelist.ui.adapters.OnItemClickListener;
import org.erickzarat.android.facebookrecipes.recipemain.di.DaggerRecipeMainComponent;
import org.erickzarat.android.facebookrecipes.recipemain.di.RecipeMainComponent;
import org.erickzarat.android.facebookrecipes.recipemain.di.RecipeMainModule;
import org.erickzarat.android.facebookrecipes.recipemain.ui.RecipeMainActivity;
import org.erickzarat.android.facebookrecipes.recipemain.ui.RecipeMainView;

/**
 * Created by zarathos on 30/06/16
 */
public class FacebookRecipesApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFacebook();
        initDB();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        DBTearDown();
    }

    private void DBTearDown() {
        FlowManager.destroy();
    }

    private void initDB() {
        FlowManager.init(this);
    }

    private void initFacebook() {
        FacebookSdk.sdkInitialize(this);
    }

    public void logout() {
        LoginManager.getInstance().logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                |Intent.FLAG_ACTIVITY_NEW_TASK
                |Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public RecipeMainComponent getRecipeComponent(RecipeMainActivity activity, RecipeMainView view){
        return DaggerRecipeMainComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .recipeMainModule(new RecipeMainModule(view))
                .build();
    }


    public RecipeListComponent getRecipeListComponent(RecipeListActivity activity, RecipeListView view, OnItemClickListener clickListener){
        return DaggerRecipeListComponent
                .builder()
                .libsModule(new LibsModule(activity))
                .recipeListModule(new RecipeListModule(view,clickListener))
                .build();
    }


}
