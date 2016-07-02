package org.erickzarat.android.facebookrecipes.recipemain;

import org.erickzarat.android.facebookrecipes.entities.Recipe;
import org.erickzarat.android.facebookrecipes.libs.base.EventBus;
import org.erickzarat.android.facebookrecipes.recipemain.events.RecipeMainEvent;
import org.erickzarat.android.facebookrecipes.recipemain.ui.RecipeMainView;
import org.greenrobot.eventbus.Subscribe;

/**
 * Created by zarathos on 1/07/16
 */
public class RecipeMainPresenterImpl implements RecipeMainPresenter {
    private RecipeMainView view;
    private EventBus eventBus;
    SaveRecipeInteractor saveInteractor;
    GetNextRecipeInteractor getNextInteractor;

    public RecipeMainPresenterImpl(RecipeMainView view, EventBus eventBus, SaveRecipeInteractor saveInteractor, GetNextRecipeInteractor getNextInteractor) {
        this.view = view;
        this.eventBus = eventBus;
        this.saveInteractor = saveInteractor;
        this.getNextInteractor = getNextInteractor;
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        eventBus.unregister(this);
        view = null;
    }

    @Override
    public void dismissRecipe() {
        if (this.view != null ){
            view.dismissAnimation();
        }
        getNextRecipe();
    }

    @Override
    public void getNextRecipe() {
        if (this.view != null ){
            view.hideUIElements();
            view.showProgress();
        }
        getNextInteractor.excecute();
    }

    @Override
    public void saveRecipe(Recipe recipe) {
        if (this.view != null ){
            view.saveAnimation();
            view.hideUIElements();
            view.showProgress();
        }
        saveInteractor.excecute(recipe);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RecipeMainEvent event) {
        if (this.view != null ){
            String error = event.getError();
            if (error != null){
                view.hideProgress();
                view.onGetRecipeError(error);
            }else {
                if (event.getType() == RecipeMainEvent.NEXT_EVENT){
                    view.setRecipe(event.getRecipe());
                } else if (event.getType() == RecipeMainEvent.SAVE_EVENT){
                    view.onRecipeSaved();
                    getNextInteractor.excecute();
                }
            }
        }
    }

    @Override
    public void imageReady() {
        if (this.view != null ){
            view.hideProgress();
            view.showUIElements();
        }
    }

    @Override
    public void imageError(String error) {
        if (this.view != null){
            view.onGetRecipeError(error);
        }
    }

    @Override
    public RecipeMainView getView() {
        return this.view;
    }
}
