package com.krp.bakingapp.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.krp.bakingapp.R;

/**
 * Created by Rakesh Praneeth.
 * This Activity is just used as a Launcher.
 * It redirects to the specified activity which acts like a starting screen.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigateToActivity();
    }

    private void navigateToActivity(){
        Intent intent = new Intent(this,RecipeListActivity.class);
        startActivity(intent);
        finish();
    }
}
