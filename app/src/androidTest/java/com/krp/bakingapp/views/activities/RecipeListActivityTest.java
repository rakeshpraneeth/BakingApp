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
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Rakesh Praneeth.
 */
@RunWith(AndroidJUnit4.class)
public class RecipeListActivityTest {

    @Rule
    public ActivityTestRule<RecipeListActivity> mActivityTestRule = new ActivityTestRule<>(RecipeListActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();

        Espresso.registerIdlingResources(mIdlingResource);
    }

    @Test
    public void shouldShowProperText_whenFirstItemIsObtained() {

        String RECIPE = "Nutella Pie";

        onView(withId(R.id.recipeListRV))
                .perform(RecyclerViewActions.scrollToPosition(0));
        onView(withText(RECIPE)).check(matches(isDisplayed()));
    }

    @Test
    public void shouldShowCorrectText_whenItemIsObtained() {

        String NAME = "Yellow Cake";

        onView(withId(R.id.recipeListRV))
                .perform(RecyclerViewActions.scrollToPosition(2));
        onView(withText(NAME)).check(matches(isDisplayed()));
        onView(withText(NAME)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            Espresso.unregisterIdlingResources(mIdlingResource);
        }
    }
}