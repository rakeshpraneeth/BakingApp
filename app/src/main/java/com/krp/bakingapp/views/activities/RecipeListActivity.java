package com.krp.bakingapp.views.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;

import com.krp.bakingapp.R;
import com.krp.bakingapp.idlingResource.BaSimpleIdlingResource;
import com.krp.bakingapp.utilities.FragmentUtils;
import com.krp.bakingapp.views.fragments.RecipeListFragment;

public class RecipeListActivity extends AppCompatActivity {

    @Nullable
    private BaSimpleIdlingResource mIdlingResource;

    /**
     * Only called from test, creates and returns a new {@link BaSimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new BaSimpleIdlingResource();
        }
        return mIdlingResource;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_recipe_list);

        getIdlingResource();
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(false);
        }
        if(savedInstanceState == null) {
            FragmentUtils.addFragment(this, R.id.recipeListContainer,
                    RecipeListFragment.newInstance(), RecipeListFragment.TAG);
        }
    }

    @Nullable
    public BaSimpleIdlingResource getmIdlingResource() {
        return mIdlingResource;
    }
}
