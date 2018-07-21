package com.krp.bakingapp.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.krp.bakingapp.R;
import com.krp.bakingapp.databinding.ActivityRecipeDetailsBinding;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.utilities.FragmentUtils;
import com.krp.bakingapp.views.fragments.RecipeDetailsFragment;

public class RecipeDetailsActivity extends AppCompatActivity {

    ActivityRecipeDetailsBinding binding;
    public static final String RECIPE_MODEL = "recipeModel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_details);

        if (savedInstanceState == null) {
            Recipe recipe = getIntent().getParcelableExtra(RECIPE_MODEL);
            if (recipe != null) {
                setTitle(recipe.getName());
                FragmentUtils.addFragment(this, R.id.recipeDetailsContainer,
                        RecipeDetailsFragment.newInstance(recipe), RecipeDetailsFragment.TAG);
            }
        }
    }
}
