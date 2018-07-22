package com.krp.bakingapp.views.fragments;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.krp.bakingapp.R;
import com.krp.bakingapp.adapters.RecipeStepsAdapter;
import com.krp.bakingapp.databinding.FragmentRecipeDetailsBinding;
import com.krp.bakingapp.interfaces.OnRecipeStepsRvItemClicked;
import com.krp.bakingapp.model.Ingredient;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.viewModels.RecipeDetailViewModel;
import com.krp.bakingapp.views.activities.RecipeStepInfoActivity;

import java.util.List;

public class RecipeDetailsFragment extends Fragment implements OnRecipeStepsRvItemClicked {

    public static final String TAG = RecipeDetailsFragment.class.getSimpleName();
    public static final String RECIPE_ITEM = "recipeItem";

    FragmentRecipeDetailsBinding binding;
    Recipe recipe;
    RecipeDetailViewModel viewModel;
    RecipeStepsAdapter adapter;

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
                adapter = new RecipeStepsAdapter(this);
                viewModel = new RecipeDetailViewModel(recipe);
                viewModel.setAdapter(adapter);
                viewModel.showData();
                parseIngredientsData(recipe.getIngredients());
            }
        }
        initializeRv();
        binding.setViewModel(viewModel);
    }

    private void parseIngredientsData(List<Ingredient> ingredientList) {
        StringBuilder builder = new StringBuilder();
        int pointNumber = 1;
        for (Ingredient ingredient : ingredientList) {
            builder.append("<b>")
                    .append(pointNumber)
                    .append(". ")
                    .append("</b>")
                    .append(" Need ")
                    .append(ingredient.getQuantity())
                    .append(ingredient.getMeasure())
                    .append(" of ")
                    .append(ingredient.getIngredient())
                    .append("<br/>");
            pointNumber++;
        }
        setIngredientsText(builder.toString());
    }

    private void setIngredientsText(String ingredientsText) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            binding.ingredientsTV.setText(Html.fromHtml(ingredientsText, Html.FROM_HTML_MODE_LEGACY));
        } else {
            binding.ingredientsTV.setText(Html.fromHtml(ingredientsText));
        }
    }

    private Recipe getRecipe() {
        return getArguments().getParcelable(RECIPE_ITEM);
    }

    private void initializeRv() {
        binding.recipeDetailsRV.setHasFixedSize(true);
        binding.recipeDetailsRV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recipeDetailsRV.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int position) {
        Intent intent = new Intent(getContext(), RecipeStepInfoActivity.class);
        intent.putExtra(RecipeStepInfoActivity.RECIPE_OBJ, recipe);
        intent.putExtra(RecipeStepInfoActivity.RECIPE_STEP_OBJ, recipe.getSteps().get(position));
        getContext().startActivity(intent);
    }

}
