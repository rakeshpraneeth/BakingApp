package com.krp.bakingapp.viewModels;

import com.krp.bakingapp.adapters.RecipeAdapter;
import com.krp.bakingapp.model.Ingredient;
import com.krp.bakingapp.model.Recipe;

/**
 * Created by Rakesh Praneeth.
 */
public class RecipeDetailViewModel {

    private Recipe recipe;
    private RecipeAdapter adapter;

    public RecipeDetailViewModel(Recipe recipe) {
        this.recipe = recipe;
    }

    public void setAdapter(RecipeAdapter adapter) {
        this.adapter = adapter;
    }

    public void showData() {
        showIngredientListItem();
    }

    private void showIngredientListItem() {
        StringBuilder builder = new StringBuilder();
        int pointNumber = 1;
        for (Ingredient ingredient : recipe.getIngredients()) {
            builder.append("<b>")
                    .append(pointNumber)
                    .append(". ")
                    .append("</b>")
                    .append(" Need ")
                    .append(ingredient.getQuantity())
                    .append(ingredient.getMeasure())
                    .append(" of ")
                    .append(ingredient.getIngredient())
                    .append("<br/>");
            pointNumber++;
        }
        if (adapter != null) {
            adapter.add(new IngredientItemViewModel(builder.toString()));
        }
    }
}
