package com.krp.bakingapp.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.krp.bakingapp.R;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.utilities.FragmentUtils;
import com.krp.bakingapp.views.fragments.RecipeStepInfoFragment;

public class RecipeStepInfoActivity extends AppCompatActivity {

    public static final String RECIPE_OBJ = "recipeObj";
    public static final String STEP_POSITION = "stepPosition";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_info);

        if (savedInstanceState == null) {
            Recipe recipe = getIntent().getParcelableExtra(RECIPE_OBJ);
            int stepPosition = getIntent().getIntExtra(STEP_POSITION,-1);
            FragmentUtils.addFragment(this, R.id.recipeStepInfoContainer,
                    RecipeStepInfoFragment.newInstance(recipe, stepPosition), RecipeStepInfoFragment.TAG);
        }
    }
}
