package com.krp.bakingapp.viewModels;

import android.content.Context;
import android.databinding.ObservableInt;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;

import com.krp.bakingapp.adapters.RecipeAdapter;
import com.krp.bakingapp.common.BaseUrl;
import com.krp.bakingapp.interfaces.BaApiService;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.utilities.NetworkHandler;
import com.krp.bakingapp.views.activities.RecipeListActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rakesh Praneeth.
 */
public class RecipeListViewModel implements Parcelable {

    private RecipeAdapter adapter;
    private WeakReference<Context> contextWeakReference;
    private BaApiService baApiService;

    private ObservableInt noInternetVisibility;
    private ObservableInt progressbarVisibility;
    private ObservableInt retrievalFailureMsgVisibility;
    private ObservableInt recyclerViewVisibility;

    private List<RowViewModel> recipeList;


    public RecipeListViewModel(Context context) {
        contextWeakReference = new WeakReference<>(context);
        recipeList = new ArrayList<>();

        noInternetVisibility = new ObservableInt(View.GONE);
        progressbarVisibility = new ObservableInt(View.VISIBLE);
        retrievalFailureMsgVisibility = new ObservableInt(View.GONE);
        recyclerViewVisibility = new ObservableInt(View.GONE);

        fetchRecipes();
    }

    public ObservableInt getNoInternetVisibility() {
        return noInternetVisibility;
    }

    public ObservableInt getProgressbarVisibility() {
        return progressbarVisibility;
    }

    public ObservableInt getRetrievalFailureMsgVisibility() {
        return retrievalFailureMsgVisibility;
    }

    public ObservableInt getRecyclerViewVisibility() {
        return recyclerViewVisibility;
    }

    public void setAdapter(RecipeAdapter adapter) {
        this.adapter = adapter;
    }

    public List<RowViewModel> getRecipeList() {
        return recipeList;
    }

    private void fetchRecipes() {

        if (contextWeakReference.get() != null) {

            if (NetworkHandler.isNetworkAvailable(contextWeakReference.get())) {
                // If network is available.
                makeServiceCall();

            } else {
                // If network is not available.
                noInternetVisibility.set(View.VISIBLE);
                progressbarVisibility.set(View.GONE);
            }
        }
    }

    private void makeServiceCall() {
        if (baApiService == null) {
            baApiService = BaseUrl.getBaApiService();
        }

        baApiService.getRecipeList().enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.isSuccessful()) {
                    updateList(response.body());

                }
                progressbarVisibility.set(View.GONE);
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                progressbarVisibility.set(View.GONE);
                retrievalFailureMsgVisibility.set(View.VISIBLE);
            }
        });

    }

    public void updateList(List<Recipe> data) {

        // Need this for Espresso testing.
        try {
            RecipeListActivity activity = (RecipeListActivity) contextWeakReference.get();
            activity.getmIdlingResource().setIdleState(true);
        }catch (Exception e){

        }

        if (adapter != null) {
            if (recipeList != null) {
                recipeList.clear();
            }
            for (Recipe recipe : data) {
                recipeList.add(new RecipeItemViewModel(recipe));
            }
            adapter.setList(recipeList);
            recyclerViewVisibility.set(View.VISIBLE);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }

    protected RecipeListViewModel(Parcel in) {
    }

    public static final Parcelable.Creator<RecipeListViewModel> CREATOR = new Parcelable.Creator<RecipeListViewModel>() {
        @Override
        public RecipeListViewModel createFromParcel(Parcel source) {
            return new RecipeListViewModel(source);
        }

        @Override
        public RecipeListViewModel[] newArray(int size) {
            return new RecipeListViewModel[size];
        }
    };

}
