package com.krp.bakingapp.viewModels;

import android.os.Parcel;

import com.krp.bakingapp.R;

/**
 * Created by Rakesh Praneeth.
 */
public class IngredientItemViewModel extends RowViewModel{

    String ingredient;

    public IngredientItemViewModel(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getIngredient() {
        return ingredient;
    }

    @Override
    public int getLayout() {
        return R.layout.item_ingredients;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.ingredient);
    }

    protected IngredientItemViewModel(Parcel in) {
        this.ingredient = in.readString();
    }

    public static final Creator<IngredientItemViewModel> CREATOR = new Creator<IngredientItemViewModel>() {
        @Override
        public IngredientItemViewModel createFromParcel(Parcel source) {
            return new IngredientItemViewModel(source);
        }

        @Override
        public IngredientItemViewModel[] newArray(int size) {
            return new IngredientItemViewModel[size];
        }
    };
}
