package com.krp.bakingapp.services;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.krp.bakingapp.BakingAppWidgetProvider;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.utilities.BaSharedPreferenceUtil;

/**
 * Created by Rakesh Praneeth.
 */
public class BaRecipeService extends IntentService {

    private static final String ACTION_UPDATE_WIDGETS = "com.krp.bakingapp.services.action.update.widgets";
    private static final String ACTION_UPDATE_RECIPE = "com.krp.bakingapp.services.action.update.recipe";
    private static final String EXTRA_RECIPE = "com.krp.bakingapp.services.extra.recipe";

    public BaRecipeService() {
        super("BaRecipeService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        if (intent != null) {
            final String action = intent.getAction();
            if (action.equals(ACTION_UPDATE_WIDGETS)) {
                handleActionUpdateWidgets();
            } else if (action.equals(ACTION_UPDATE_RECIPE)) {
                Recipe recipe = intent.getParcelableExtra(EXTRA_RECIPE);
                handleActionUpdateRecipe(recipe);
            }
        }
    }

    // Method to start the intent service to update the widgets.
    public static void startActionUpdateIngredientsWidgets(Context context) {

        Intent intent = new Intent(context, BaRecipeService.class);
        intent.setAction(ACTION_UPDATE_WIDGETS);
        context.startService(intent);
    }

    // Method to start intent service to store in  shared preferences and update the widgets.
    public static void startActionToUpdateRecipe(Context context, Recipe recipe) {
        Intent intent = new Intent(context, BaRecipeService.class);
        intent.setAction(ACTION_UPDATE_RECIPE);
        intent.putExtra(EXTRA_RECIPE, recipe);
        context.startService(intent);
    }

    // Method to handle the recipe object and start widget update service.
    private void handleActionUpdateRecipe(Recipe recipe) {
        BaSharedPreferenceUtil.storeRecentlyClickedRecipe(this, recipe);
        startActionUpdateIngredientsWidgets(this);
    }

    // Method to handle the update of widgets.
    private void handleActionUpdateWidgets() {

        // Getting the recipe from the shared preference.
        Recipe recipe = BaSharedPreferenceUtil.getRecentlyChangedRecipe(this);

        if (recipe != null) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, BakingAppWidgetProvider.class));

            BakingAppWidgetProvider.updatePlantWidgets(this, appWidgetManager, recipe, appWidgetIds);
        }
    }
}
