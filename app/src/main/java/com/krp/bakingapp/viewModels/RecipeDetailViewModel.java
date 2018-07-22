package com.krp.bakingapp.viewModels;

import com.krp.bakingapp.adapters.RecipeStepsAdapter;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.model.Step;

/**
 * Created by Rakesh Praneeth.
 */
public class RecipeDetailViewModel {

    private Recipe recipe;
    private RecipeStepsAdapter adapter;

    public RecipeDetailViewModel(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setAdapter(RecipeStepsAdapter adapter) {
        this.adapter = adapter;
    }

    public void showData() {
        showRecipeSteps();
    }

    private void showRecipeSteps() {
        if (adapter != null) {
            for (Step step : recipe.getSteps()) {
                adapter.add(step.getShortDescription());
            }
        }
    }
}
