package com.krp.bakingapp.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.krp.bakingapp.R;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.model.Step;
import com.krp.bakingapp.utilities.FragmentUtils;
import com.krp.bakingapp.views.fragments.RecipeStepInfoFragment;

public class RecipeStepInfoActivity extends AppCompatActivity {

    public static final String RECIPE_OBJ = "recipeObj";
    public static final String RECIPE_STEP_OBJ = "recipeStepObj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_info);

        if (savedInstanceState == null) {
            Recipe recipe = getIntent().getParcelableExtra(RECIPE_OBJ);
            Step step = getIntent().getParcelableExtra(RECIPE_STEP_OBJ);
            FragmentUtils.addFragment(this, R.id.recipeStepInfoContainer,
                    RecipeStepInfoFragment.newInstance(recipe, step), RecipeStepInfoFragment.TAG);
        }
    }
}
