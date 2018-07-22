package com.krp.bakingapp.interfaces;

import com.krp.bakingapp.model.Recipe;

/**
 * Created by Rakesh Praneeth.
 */
public interface OnRecipeStepSelectedCallback {

    void onRecipeSelected(Recipe recipe, int position);
}
