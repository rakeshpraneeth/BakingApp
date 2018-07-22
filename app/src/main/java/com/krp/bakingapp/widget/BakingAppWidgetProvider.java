package com.krp.bakingapp.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.krp.bakingapp.R;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.services.BaRecipeService;
import com.krp.bakingapp.views.activities.RecipeDetailsActivity;
import com.krp.bakingapp.views.activities.RecipeListActivity;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                Recipe recipe, int appWidgetId) {


        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.baking_app_widget_provider);
        Intent intent;

        if (recipe != null) {
            // If obtained recipe is not null, the show the recipe details and navigate user to details screen when clicked on widget.

            views.setTextViewText(R.id.recipeWidgetValueTV, recipe.getName());

            Intent listService = new Intent(context, IngredientsWidgetLvService.class);
            views.setRemoteAdapter(R.id.ingredients_list, listService);

            views.setEmptyView(R.id.ingredients_list, R.id.empty_ingredient_view);

            intent = new Intent(context, RecipeDetailsActivity.class);
            intent.putExtra(RecipeDetailsActivity.RECIPE_MODEL, recipe);

        } else {
            // If obtained recipe is null, show N/A and navigate user to RecipeListActivity to show list of recipes.
            intent = new Intent(context, RecipeListActivity.class);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.baking_app_widget_layout, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        BaRecipeService.startActionUpdateIngredientsWidgets(context);
    }

    public static void updatePlantWidgets(Context context, AppWidgetManager appWidgetManager,
                                          Recipe recipe, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipe, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
    }

    @Override
    public void onDisabled(Context context) {
    }
}

