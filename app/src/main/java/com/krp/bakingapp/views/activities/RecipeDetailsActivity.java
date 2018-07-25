package com.krp.bakingapp.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.krp.bakingapp.R;
import com.krp.bakingapp.interfaces.OnRecipeStepSelectedCallback;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.utilities.FragmentUtils;
import com.krp.bakingapp.views.fragments.RecipeDetailsFragment;
import com.krp.bakingapp.views.fragments.RecipeStepInfoFragment;

public class RecipeDetailsActivity extends AppCompatActivity implements OnRecipeStepSelectedCallback {


    public static final String RECIPE_MODEL = "recipeModel";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DataBindingUtil.setContentView(this, R.layout.activity_recipe_details);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        if (savedInstanceState == null) {
            Recipe recipe = getIntent().getParcelableExtra(RECIPE_MODEL);
            if (recipe != null) {
                setTitle(recipe.getName());
                FragmentUtils.addFragment(this, R.id.recipeDetailsContainer,
                        RecipeDetailsFragment.newInstance(recipe), RecipeDetailsFragment.TAG);
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRecipeSelected(Recipe recipe, int position) {

        Fragment fragment = RecipeStepInfoFragment.newInstance(recipe, position);
        FragmentUtils.replaceFragment(this, R.id.recipeStepDetailsContainerTablet, fragment, RecipeStepInfoFragment.TAG);
    }
}
