package com.krp.bakingapp.viewModels;

import android.content.Intent;
import android.os.Parcel;
import android.view.View;

import com.krp.bakingapp.R;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.views.activities.RecipeDetailsActivity;

/**
 * Created by Rakesh Praneeth.
 */
public class RecipeItemViewModel extends RowViewModel {

    Recipe recipe;

    public RecipeItemViewModel(Recipe recipe) {
        this.recipe = recipe;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    @Override
    public int getLayout() {
        return R.layout.item_recipe;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected RecipeItemViewModel(Parcel in) {
    }

    public static final Creator<RecipeItemViewModel> CREATOR = new Creator<RecipeItemViewModel>() {
        @Override
        public RecipeItemViewModel createFromParcel(Parcel source) {
            return new RecipeItemViewModel(source);
        }

        @Override
        public RecipeItemViewModel[] newArray(int size) {
            return new RecipeItemViewModel[size];
        }
    };

    public void onRecipeItemClicked(View view) {
        if (view != null && recipe != null) {
            Intent intent = new Intent(view.getContext(), RecipeDetailsActivity.class);
            intent.putExtra(RecipeDetailsActivity.RECIPE_MODEL, recipe);
            view.getContext().startActivity(intent);
        }
    }
}
