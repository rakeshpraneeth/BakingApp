package com.krp.bakingapp.viewModels;

import android.os.Parcel;

import com.krp.bakingapp.R;
import com.krp.bakingapp.model.Recipe;

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
}
