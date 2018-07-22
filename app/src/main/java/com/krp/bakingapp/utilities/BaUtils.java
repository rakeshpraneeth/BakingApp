package com.krp.bakingapp.utilities;

import com.krp.bakingapp.model.Ingredient;

import java.util.List;

/**
 * Created by Rakesh Praneeth.
 */
public final class BaUtils {

    private BaUtils() {
    }

    // Method to parse the list of ingredients into a html string.
    public static String parseIngredientsToHtmlString(List<Ingredient> ingredientList) {
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
        return builder.toString();
    }

    public static String getStringByIngredient(Ingredient ingredient) {
        StringBuilder builder = new StringBuilder();
        builder.append(ingredient.getQuantity())
                .append(ingredient.getMeasure())
                .append(" of ")
                .append(ingredient.getIngredient());
        return builder.toString();
    }
}
