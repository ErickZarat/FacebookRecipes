package org.erickzarat.android.facebookrecipes.recipelist.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.bumptech.glide.Glide;
import org.erickzarat.android.facebookrecipes.FacebookRecipesApp;
import org.erickzarat.android.facebookrecipes.R;
import org.erickzarat.android.facebookrecipes.entities.Recipe;
import org.erickzarat.android.facebookrecipes.libs.GlideImageLoader;
import org.erickzarat.android.facebookrecipes.libs.base.ImageLoader;
import org.erickzarat.android.facebookrecipes.recipelist.RecipeListPresenter;
import org.erickzarat.android.facebookrecipes.recipelist.di.RecipeListComponent;
import org.erickzarat.android.facebookrecipes.recipelist.events.RecipeListEvent;
import org.erickzarat.android.facebookrecipes.recipelist.ui.adapters.OnItemClickListener;
import org.erickzarat.android.facebookrecipes.recipelist.ui.adapters.RecipesAdapter;
import org.erickzarat.android.facebookrecipes.recipemain.ui.RecipeMainActivity;

import java.util.Arrays;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity implements RecipeListView, OnItemClickListener{

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    RecipesAdapter adapter;
    RecipeListPresenter presenter;
    RecipeListComponent component;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);
        ButterKnife.bind(this);

        setupToolbar();
        setupInjection();
        setupRecyclerrView();

        presenter.onCreate();
        presenter.getRecipes();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recipe_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_main){
            navigateToMainScreen();
        } else if (item.getItemId() == R.id.action_logout){
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FacebookRecipesApp app = (FacebookRecipesApp) getApplication();
        app.logout();
    }

    private void navigateToMainScreen() {
        startActivity(new Intent(this, RecipeMainActivity.class));
    }


    private void setupInjection() {
        FacebookRecipesApp app = (FacebookRecipesApp) getApplication();
        component = app.getRecipeListComponent(this, this, this);
        presenter = getPresenter();
        adapter = getAdapter();
    }

    private void setupRecyclerrView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
    }

    @OnClick(R.id.toolbar)
    public void onToolbarClick(){
        recyclerView.smoothScrollToPosition(0);
    }

    @Override
    public void setRecipes(List<Recipe> data) {
        adapter.setRecipes(data);
    }

    @Override
    public void recipeUpdated() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void recipeDeleted(Recipe recipe) {
        adapter.removeRecipe(recipe);
    }

    @Override
    public void onFavClick(Recipe recipe) {
        presenter.toggleFavorite(recipe);
    }

    @Override
    public void onItemClick(Recipe recipe) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recipe.getSourceURL()));
        startActivity(intent);
    }

    @Override
    public void onDeleteClick(Recipe recipe) {
        presenter.removeRecipe(recipe);
    }

    public RecipeListPresenter getPresenter() {
        return component.getPresenter();
    }

    public RecipesAdapter getAdapter() {
        return component.getAdapter();
    }
}
