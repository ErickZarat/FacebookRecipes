package org.erickzarat.android.facebookrecipes.recipelist;

import org.erickzarat.android.facebookrecipes.entities.Recipe;

/**
 * Created by zarathos on 1/07/16
 */
public interface StoredRecipesInteractor {
    void executeUpdate(Recipe recipe);
    void executeDelete(Recipe recipe);
}
