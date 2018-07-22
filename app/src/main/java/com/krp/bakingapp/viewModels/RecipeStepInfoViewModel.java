package com.krp.bakingapp.viewModels;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;

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
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.krp.bakingapp.R;
import com.krp.bakingapp.model.Recipe;
import com.krp.bakingapp.model.Step;

/**
 * Created by Rakesh Praneeth.
 */
public class RecipeStepInfoViewModel {

    Recipe recipe;
    int currentStepPosition;

    private static ExoPlayer mExoPlayer;
    private Context context;

    private ObservableField<String> instructions;
    private ObservableInt previousBtnVisibility;
    private ObservableInt nextBtnVisibility;
    private ObservableBoolean isExoPlayerInitialized;

    public RecipeStepInfoViewModel(Context context, Recipe recipe, int  currentStepPosition) {
        this.context = context;
        this.recipe = recipe;
        this.currentStepPosition = currentStepPosition;

        instructions = new ObservableField<>("");
        previousBtnVisibility = new ObservableInt(View.GONE);
        nextBtnVisibility = new ObservableInt(View.GONE);
        isExoPlayerInitialized = new ObservableBoolean(false);
        initializeData(recipe.getSteps().get(currentStepPosition));
    }

    public ObservableField<String> getInstructions() {
        return instructions;
    }

    public ObservableInt getPreviousBtnVisibility() {
        return previousBtnVisibility;
    }

    public ObservableInt getNextBtnVisibility() {
        return nextBtnVisibility;
    }

    public ObservableBoolean getIsExoPlayerInitialized() {
        return isExoPlayerInitialized;
    }

    private void initializeData(Step step) {
        if (step != null) {
            if (!step.getVideoURL().isEmpty()) {
                initializeExoPlayer(Uri.parse(step.getVideoURL()));
            } else if (!step.getThumbnailURL().isEmpty()) {
                initializeExoPlayer(Uri.parse(step.getThumbnailURL()));
            }
            instructions.set(step.getDescription());
            previousStepBtnVisibility();
            nextStepBtnVisibility();
        }
    }

    private void initializeExoPlayer(Uri videoUri) {
        if (mExoPlayer == null) {

            RenderersFactory renderersFactory = new DefaultRenderersFactory(context);
            TrackSelector trackSelector = new DefaultTrackSelector();
            LoadControl loadControl = new DefaultLoadControl();
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector, loadControl);
            isExoPlayerInitialized.set(true);
        }
        MediaSource mediaSource = buildMediaSource(videoUri);
        mExoPlayer.prepare(mediaSource);
        mExoPlayer.setPlayWhenReady(true);
    }

    private MediaSource buildMediaSource(Uri videoUri) {

        // Measures bandwidth during playback. Can be null if not required.
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();

        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                Util.getUserAgent(context, "BakingApp"), bandwidthMeter);

        // This is the MediaSource representing the media to be played.
        return new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(videoUri);
    }

    public void releaseExoPlayer() {
        if (mExoPlayer != null) {
            mExoPlayer.stop();
            mExoPlayer.release();
            mExoPlayer = null;
        }
    }

    @BindingAdapter("setPlayerView")
    public static void setPlayerView(PlayerView playerView, boolean isExoPlayerInitialized) {
        if (playerView != null) {

            if (!isExoPlayerInitialized) {
                playerView.setDefaultArtwork(BitmapFactory.decodeResource(playerView.getResources(),
                        R.drawable.placeholder));
            } else {
                playerView.setPlayer(mExoPlayer);
            }
        }
    }

    public void onPreviousBtnClicked(View view) {
        currentStepPosition = currentStepPosition - 1;
        if (recipe.getSteps().get(currentStepPosition) != null) {
            initializeData(recipe.getSteps().get(currentStepPosition));
        }
    }

    public void onNextBtnClicked(View view) {
        currentStepPosition = currentStepPosition + 1;
        if (recipe.getSteps().get(currentStepPosition) != null) {
            initializeData(recipe.getSteps().get(currentStepPosition));
        }
    }

    // Method to handle previous button visibility depending on the current step.
    private void previousStepBtnVisibility() {

        if (currentStepPosition == 0) {
            // Selected step position is the first position
            previousBtnVisibility.set(View.GONE);
        } else {
            previousBtnVisibility.set(View.VISIBLE);
        }
    }

    // Method to handle next button visibility depending on the current step.
    private void nextStepBtnVisibility() {
        if (currentStepPosition >= recipe.getSteps().size() - 1) {
            // If the selected step position is the last position
            nextBtnVisibility.set(View.GONE);
        } else {
            nextBtnVisibility.set(View.VISIBLE);
        }
    }
}
