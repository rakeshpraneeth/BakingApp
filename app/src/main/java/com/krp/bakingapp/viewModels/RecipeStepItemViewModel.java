package com.krp.bakingapp.viewModels;

import android.content.Intent;
import android.os.Parcel;
import android.view.View;

import com.krp.bakingapp.R;
import com.krp.bakingapp.model.Step;
import com.krp.bakingapp.views.activities.RecipeStepInfoActivity;

/**
 * Created by Rakesh Praneeth.
 */
public class RecipeStepItemViewModel extends RowViewModel {

    private Step step;

    public RecipeStepItemViewModel(Step step) {
        this.step = step;
    }

    public Step getStep() {
        return step;
    }

    @Override
    public int getLayout() {
        return R.layout.item_recipe_step;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.step, flags);
    }

    protected RecipeStepItemViewModel(Parcel in) {
        this.step = in.readParcelable(Step.class.getClassLoader());
    }

    public static final Creator<RecipeStepItemViewModel> CREATOR = new Creator<RecipeStepItemViewModel>() {
        @Override
        public RecipeStepItemViewModel createFromParcel(Parcel source) {
            return new RecipeStepItemViewModel(source);
        }

        @Override
        public RecipeStepItemViewModel[] newArray(int size) {
            return new RecipeStepItemViewModel[size];
        }
    };

    public void onRecipeStepClicked(View view) {
        if (view != null && step != null) {
            Intent intent = new Intent(view.getContext(), RecipeStepInfoActivity.class);
            intent.putExtra(RecipeStepInfoActivity.RECIPE_STEP_OBJ, step);
            view.getContext().startActivity(intent);
        }
    }
}
