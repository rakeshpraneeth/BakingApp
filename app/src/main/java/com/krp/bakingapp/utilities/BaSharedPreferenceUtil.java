package com.krp.bakingapp.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.krp.bakingapp.model.Recipe;

/**
 * Created by Rakesh Praneeth.
 */
public final class BaSharedPreferenceUtil {

    private static final String APP_SHARED_PREF_FILE_NAME = "baking_app_shared_pref";
    private static final String RECENTLY_CHANGED_RECIPE = "shared_pref_recently_changed_recipe";

    private BaSharedPreferenceUtil() {
    }

    // Method to store the recipe object in the shared preference.
    public static void storeRecentlyClickedRecipe(Context context, Recipe recipe) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_SHARED_PREF_FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String recipeString = new Gson().toJson(recipe);
        editor.putString(RECENTLY_CHANGED_RECIPE, recipeString);
        editor.apply();
    }

    // Method to get the recipe object from the shared preference.
    public static Recipe getRecentlyChangedRecipe(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APP_SHARED_PREF_FILE_NAME,
                Context.MODE_PRIVATE);

        String recipeString = sharedPreferences.getString(RECENTLY_CHANGED_RECIPE, null);
        Recipe recipe = new Gson().fromJson(recipeString, Recipe.class);
        return recipe;
    }
}
