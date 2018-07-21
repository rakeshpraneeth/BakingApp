package com.krp.bakingapp.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.krp.bakingapp.R;
import com.krp.bakingapp.databinding.ActivityRecipeListBinding;
import com.krp.bakingapp.utilities.FragmentUtils;
import com.krp.bakingapp.views.RecipeListFragment;

public class RecipeListActivity extends AppCompatActivity {

    ActivityRecipeListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recipe_list);

        if(savedInstanceState == null) {
            FragmentUtils.addFragment(this, R.id.recipeListContainer,
                    RecipeListFragment.newInstance(), RecipeListFragment.TAG);
        }
    }
}
