package com.krp.bakingapp.views.fragments;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krp.bakingapp.R;
import com.krp.bakingapp.adapters.RecipeAdapter;
import com.krp.bakingapp.databinding.FragmentRecipeDetailsBinding;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.viewModels.RecipeDetailViewModel;

public class RecipeDetailsFragment extends Fragment {

    public static final String TAG = RecipeDetailsFragment.class.getSimpleName();
    public static final String RECIPE_ITEM = "recipeItem";

    FragmentRecipeDetailsBinding binding;
    Recipe recipe;
    RecipeDetailViewModel viewModel;
    RecipeAdapter adapter;

    public static RecipeDetailsFragment newInstance(Recipe recipe) {

        Bundle args = new Bundle();
        args.putParcelable(RECIPE_ITEM, recipe);

        RecipeDetailsFragment fragment = new RecipeDetailsFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_details, container, false);

        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            recipe = getRecipe();
            if (recipe != null) {
                adapter = new RecipeAdapter();
                viewModel = new RecipeDetailViewModel(recipe);
                viewModel.setAdapter(adapter);
                viewModel.showData();
            }
        }
        initializeRv();
        binding.setViewModel(viewModel);
    }

    private Recipe getRecipe() {
        return getArguments().getParcelable(RECIPE_ITEM);
    }

    private void initializeRv() {
        binding.recipeDetailsRV.setHasFixedSize(true);
        binding.recipeDetailsRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recipeDetailsRV.setAdapter(adapter);
    }

}
