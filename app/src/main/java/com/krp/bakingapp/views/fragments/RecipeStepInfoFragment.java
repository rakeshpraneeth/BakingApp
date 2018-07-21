package com.krp.bakingapp.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krp.bakingapp.R;
import com.krp.bakingapp.model.Step;

public class RecipeStepInfoFragment extends Fragment {

    public static final String TAG = RecipeStepInfoFragment.class.getSimpleName();
    private static final String RECIPE_STEP_OBJ = RecipeStepInfoFragment.class.getSimpleName();

    public static RecipeStepInfoFragment newInstance(Step step) {

        Bundle args = new Bundle();
        args.putParcelable(RECIPE_STEP_OBJ, step);
        RecipeStepInfoFragment fragment = new RecipeStepInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipe_step_info, container, false);
    }

    private Step getRecipeStep() {
        return getArguments().getParcelable(RECIPE_STEP_OBJ);
    }

}
