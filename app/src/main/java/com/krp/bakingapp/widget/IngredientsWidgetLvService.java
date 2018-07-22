package com.krp.bakingapp.widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.krp.bakingapp.R;
import com.krp.bakingapp.model.Ingredient;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.utilities.BaSharedPreferenceUtil;
import com.krp.bakingapp.utilities.BaUtils;
import com.krp.bakingapp.views.activities.RecipeDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rakesh Praneeth.
 */
public class IngredientsWidgetLvService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientWidgetListViewFactory(getApplicationContext());
    }
}

class IngredientWidgetListViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    List<Ingredient> ingredientsList = new ArrayList<>();
    private Recipe recipe;

    public IngredientWidgetListViewFactory(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

        recipe = BaSharedPreferenceUtil.getRecentlyChangedRecipe(context);
        if (ingredientsList != null) {
            ingredientsList.clear();
        }
        ingredientsList.addAll(recipe.getIngredients());
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredientsList.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.ingredient_widget_item);
        remoteViews.setTextViewText(R.id.ingredientWidgetItemTv,
                 BaUtils.getStringByIngredient(ingredientsList.get(position)));

        Bundle extras = new Bundle();
        extras.putParcelable(RecipeDetailsActivity.RECIPE_MODEL, recipe);
        Intent intent = new Intent();
        intent.putExtras(extras);
        remoteViews.setOnClickFillInIntent(R.id.ingredientWidgetItem, intent);

        return remoteViews;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
