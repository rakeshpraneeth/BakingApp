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
import com.krp.bakingapp.databinding.FragmentRecipeListBinding;
import com.krp.bakingapp.viewModels.RecipeListViewModel;

public class RecipeListFragment extends Fragment {

    public static final String TAG = RecipeListFragment.class.getSimpleName();

    FragmentRecipeListBinding binding;
    RecipeAdapter adapter;
    RecipeListViewModel viewModel;

    public static RecipeListFragment newInstance() {
        Bundle args = new Bundle();

        RecipeListFragment fragment = new RecipeListFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_list, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            adapter = new RecipeAdapter();
            viewModel = new RecipeListViewModel(getContext());
            viewModel.setAdapter(adapter);
        }

        initializeRv();
        binding.setViewModel(viewModel);
    }

    private void initializeRv() {
        binding.recipeListRV.setHasFixedSize(true);
        binding.recipeListRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recipeListRV.setAdapter(adapter);
    }
}
