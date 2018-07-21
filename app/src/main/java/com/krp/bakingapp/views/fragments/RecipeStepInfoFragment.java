package com.krp.bakingapp.views.fragments;


import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.krp.bakingapp.R;
import com.krp.bakingapp.databinding.FragmentRecipeStepInfoBinding;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.model.Step;

public class RecipeStepInfoFragment extends Fragment {

    public static final String TAG = RecipeStepInfoFragment.class.getSimpleName();
    private static final String RECIPE_OBJ = "recipeObj";
    private static final String RECIPE_STEP_OBJ = "recipeStepObj";

    FragmentRecipeStepInfoBinding binding;
    Recipe recipe;
    Step currentStep;
    int selectedStepId;

    private ExoPlayer mExoPlayer;

    public static RecipeStepInfoFragment newInstance(Recipe recipe, Step step) {

        Bundle args = new Bundle();
        args.putParcelable(RECIPE_OBJ, recipe);
        args.putParcelable(RECIPE_STEP_OBJ, step);
        RecipeStepInfoFragment fragment = new RecipeStepInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_recipe_step_info, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (savedInstanceState == null) {
            recipe = getRecipe();
            currentStep = getRecipeStep();
            selectedStepId = currentStep.getId();

            binding.playerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.placeholder));

        }
        initializeData(currentStep);
        binding.previousStepBtn.setOnClickListener(v -> onPreviousBtnClicked());
        binding.nextStepBtn.setOnClickListener(v -> onNextBtnClicked());
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseExoPlayer();
    }

    // Method to get recipe object from arguments.
    private Recipe getRecipe() {
        return getArguments().getParcelable(RECIPE_OBJ);
    }

    // Method to get step object from arguments.
    private Step getRecipeStep() {
        return getArguments().getParcelable(RECIPE_STEP_OBJ);
    }

    // It is used to initialize the data with step object sent.
    private void initializeData(Step step) {

        if (step != null) {
            if (!step.getVideoURL().isEmpty()) {
                initializeExoPlayer(Uri.parse(step.getVideoURL()));
            } else if (!step.getThumbnailURL().isEmpty()) {
                initializeExoPlayer(Uri.parse(step.getThumbnailURL()));
            } else {
                binding.playerView.setDefaultArtwork(BitmapFactory.decodeResource(getResources(), R.drawable.placeholder));
            }
            showInstructions(step.getDescription());
            previousStepBtnVisibility(step.getId());
            nextStepBtnVisibility(step.getId());
        }
    }

    private void initializeExoPlayer(Uri videoUri) {
        if (mExoPlayer == null) {

            RenderersFactory renderersFactory = new DefaultRenderersFactory(getContext());
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector, loadControl);

            binding.playerView.setPlayer(mExoPlayer);
        }
        MediaSource mediaSource = buildMediaSource(videoUri);
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);
    }

    private MediaSource buildMediaSource(Uri videoUri) {

        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getContext(),
                Util.getUserAgent(getContext(), "BakingApp"), bandwidthMeter);

        // This is the MediaSource representing the media to be played.
        return new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(videoUri);
    }


    private void onPreviousBtnClicked() {
        selectedStepId = selectedStepId - 1;
        initializeData(recipe.getSteps().get(selectedStepId));
    }

    private void onNextBtnClicked() {
        selectedStepId = selectedStepId + 1;
        initializeData(recipe.getSteps().get(selectedStepId));
    }

    // It is used to show the instructions of the selected recipe step.
    private void showInstructions(String description) {
        binding.instructions.setText(description);
    }

    // Method to handle previous button visibility depending on the current step.
    private void previousStepBtnVisibility(int selectedStepPosition) {

        if (selectedStepPosition == 0) {
            // Selected step position is the first position
            binding.previousStepBtn.setVisibility(View.GONE);
        } else {
            binding.previousStepBtn.setVisibility(View.VISIBLE);
        }
    }

    // Method to handle next button visibility depending on the current step.
    private void nextStepBtnVisibility(int selectedStepPosition) {
        if (selectedStepPosition >= recipe.getSteps().size() - 1) {
            // If the selected step position is the last position
            binding.nextStepBtn.setVisibility(View.GONE);
        } else {
            binding.nextStepBtn.setVisibility(View.VISIBLE);
        }
    }

    private void releaseExoPlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }


}
