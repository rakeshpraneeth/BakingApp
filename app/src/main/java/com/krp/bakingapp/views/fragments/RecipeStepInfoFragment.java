package com.krp.bakingapp.views.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krp.bakingapp.R;
import com.krp.bakingapp.databinding.FragmentRecipeStepInfoBinding;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.model.Step;
import com.krp.bakingapp.viewModels.RecipeStepInfoViewModel;

public class RecipeStepInfoFragment extends Fragment {

    public static final String TAG = RecipeStepInfoFragment.class.getSimpleName();
    private static final String RECIPE_OBJ = "recipeObj";
    private static final String RECIPE_STEP_OBJ = "recipeStepObj";

    FragmentRecipeStepInfoBinding binding;
    Recipe recipe;
    Step currentStep;

    RecipeStepInfoViewModel viewModel;

    public static RecipeStepInfoFragment newInstance(Recipe recipe, Step step) {

        Bundle args = new Bundle();
        args.putParcelable(RECIPE_OBJ, recipe);
        args.putParcelable(RECIPE_STEP_OBJ, step);
        RecipeStepInfoFragment fragment = new RecipeStepInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_step_info, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            recipe = getRecipe();
            currentStep = getRecipeStep();

            viewModel = new RecipeStepInfoViewModel(getContext(), recipe, currentStep);
        }
        binding.setViewModel(viewModel);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (viewModel != null) {
            viewModel.releaseExoPlayer();
        }
    }

    // Method to get recipe object from arguments.
    private Recipe getRecipe() {
        return getArguments().getParcelable(RECIPE_OBJ);
    }

    // Method to get step object from arguments.
    private Step getRecipeStep() {
        return getArguments().getParcelable(RECIPE_STEP_OBJ);
    }
}
