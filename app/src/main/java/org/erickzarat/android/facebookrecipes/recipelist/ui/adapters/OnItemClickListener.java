package org.erickzarat.android.facebookrecipes.recipelist.ui.adapters;

import org.erickzarat.android.facebookrecipes.entities.Recipe;

/**
 * Created by zarathos on 1/07/16
 */
public interface OnItemClickListener {
    void onFavClick(Recipe recipe);
    void onItemClick(Recipe recipe);
    void onDeleteClick(Recipe recipe);
}
