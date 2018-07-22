package com.krp.bakingapp.views.activities;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.krp.bakingapp.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.core.StringStartsWith.startsWith;

/**
 * Created by Rakesh Praneeth.
 */
@RunWith(AndroidJUnit4.class)
public class RecipeStepInfoActivityTest {

    @Rule
    public ActivityTestRule<RecipeListActivity> mActivityTestRule = new ActivityTestRule<>(RecipeListActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();

        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void shouldNavigateToRecipeStepInfoActivity_andNotShowPreviousBtn() {

        // Nutella Pie clicked
        onView(withId(R.id.recipeListRV)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // check if the ingredients text is visible.
        onView(withId(R.id.ingredientLabel)).check(matches(withText("Ingredients")));

        // clicked on step-1
        onView(withId(R.id.recipeDetailsRV))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(0, click()));

        // check if the description is available
        onView(withId(R.id.instructions)).check(matches(withText(startsWith("Recipe Introduction"))));

        // check if the exoplayer is visible
        onView(withId(R.id.playerView)).check(matches(isDisplayed()));

        // Previous button should be disabled
        onView(withId(R.id.previousStepBtn)).check(matches(not(isDisplayed())));

        // next button should be visible
        onView(withId(R.id.nextStepBtn)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldNavigateToRecipeStepInfoActivity_andDisplayPreviousNextBrn() {
        // Nutella Pie clicked
        onView(withId(R.id.recipeListRV)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // check if the ingredients text is visible.
        onView(withId(R.id.ingredientLabel)).check(matches(withText("Ingredients")));

        // clicked on step-1
        onView(withId(R.id.recipeDetailsRV))
                .perform(RecyclerViewActions
                        .actionOnItemAtPosition(1, click()));

        // check if the description is available
        onView(withId(R.id.instructions)).check(matches(isDisplayed()));

        // check if the exoplayer is visible
        onView(withId(R.id.playerView)).check(matches(isDisplayed()));

        // Previous button should be displayed
        onView(withId(R.id.previousStepBtn)).check(matches(isDisplayed()));

        // next button should be not be displayed
        onView(withId(R.id.nextStepBtn)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }

}