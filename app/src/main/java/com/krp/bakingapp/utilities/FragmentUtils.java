package com.krp.bakingapp.utilities;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Rakesh Praneeth.
 * This class is used to maintain methods that perform operations with fragments.
 */
public final class FragmentUtils {

    private FragmentUtils() {
    }

    // This method is used to add a fragment to a particular containerId.
    public static void addFragment(Context context, int containerId, Fragment fragment, String TAG) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) context;
        if (appCompatActivity != null && fragment != null) {
            appCompatActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .add(containerId, fragment, TAG)
                    .commit();
        }
    }

    public static void replaceFragment(Context context, int containerId, Fragment fragment, String TAG) {
        AppCompatActivity appCompatActivity = (AppCompatActivity) context;
        if (appCompatActivity != null && fragment != null) {
            appCompatActivity.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(containerId, fragment, TAG)
                    .commit();
        }
    }
}
