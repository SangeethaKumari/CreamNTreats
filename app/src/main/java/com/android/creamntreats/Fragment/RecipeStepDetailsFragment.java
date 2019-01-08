package com.android.creamntreats.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.android.creamntreats.R;
import com.android.creamntreats.RecipeStepDetailsActivity;
import com.android.creamntreats.recipe.RecipeSteps;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;


public class RecipeStepDetailsFragment extends Fragment {
List<RecipeSteps> recipeSteps = null;
TextView stepDescription;
    private SimpleExoPlayer mExoPlayer;
    private SimpleExoPlayerView mPlayerView;
    Button nextStepButton ;
    Button previousStepButton ;
    int currentElement =0;

    /**
     * Inflates the fragment layout file and sets the correct resource for the image to display
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Load the saved state (the list of images and list index) if there is one
        if (savedInstanceState != null) {

        }
        String recipeName = getArguments().getString(getString(R.string.selected_recipe_name));

        ((RecipeStepDetailsActivity) getActivity()).setActionBarTitle(recipeName);

        Log.i("RecipeStepDetailsFragment", "onCreateView: ");

        recipeSteps =getArguments().getParcelableArrayList(getString(R.string.selected_recipe_step_bundle));
        currentElement =getArguments().getInt(getString(R.string.selected_recipe_step_index));

        View rootView = inflater.inflate(R.layout.fragment_recipe_step_details, container, false);
        // Initialize the player view.
        mPlayerView = rootView.findViewById(R.id.playerView);
        String videoUrl = recipeSteps.get(currentElement).getVideoURL();
        // Initialize the player.
        initializePlayer(Uri.parse(videoUrl));


        stepDescription = rootView.findViewById(R.id.step_description);
        stepDescription.setText(recipeSteps.get(currentElement).getDescription());

        nextStepButton =  rootView.findViewById(R.id.next_button);
        previousStepButton =  rootView.findViewById(R.id.previous_button);

        //the user can select the next step from the list
        nextStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (mExoPlayer!=null){
                    mExoPlayer.stop();
                    mExoPlayer = null;
                }
                currentElement=currentElement+1;
                Log.i("nextButton ",String.valueOf(currentElement) + " "+recipeSteps.get(currentElement).getVideoURL());
                if(currentElement<recipeSteps.size()){
                    // Initialize the player.
                    initializePlayer(Uri.parse(recipeSteps.get(currentElement).getVideoURL()));
                    stepDescription.setText(recipeSteps.get(currentElement).getDescription());
                }
                else{
                    currentElement=0;
                    // Initialize the player.
                    initializePlayer(Uri.parse(recipeSteps.get(currentElement).getVideoURL()));
                    stepDescription.setText(recipeSteps.get(currentElement).getDescription());
                }
            }
        });
        //the user can select the previous step from the list
        previousStepButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (mExoPlayer!=null){
                    mExoPlayer.stop();
                    mExoPlayer=null;
                }
                currentElement=currentElement-1;
                Log.i("previousStepButton ",String.valueOf(currentElement));


                if(currentElement>=0){
                    // Initialize the player.
                    initializePlayer(Uri.parse(recipeSteps.get(currentElement).getVideoURL()));
                    stepDescription.setText(recipeSteps.get(currentElement).getDescription());
                }
                else{
                    currentElement=0;
                    // Initialize the player.
                    initializePlayer(Uri.parse(recipeSteps.get(currentElement).getVideoURL()));
                    stepDescription.setText(recipeSteps.get(currentElement).getDescription());
                }
            }
        });


        return rootView;
    }

    /**
     * Initialize ExoPlayer.
     * @param mediaUri The URI of the sample to play.
     */
    private void initializePlayer(Uri mediaUri) {

            if (mExoPlayer == null) {
                // Create an instance of the ExoPlayer.
                TrackSelector trackSelector = new DefaultTrackSelector();
                LoadControl loadControl = new DefaultLoadControl();
                mExoPlayer = ExoPlayerFactory.newSimpleInstance(getContext(), trackSelector, loadControl);
                mPlayerView.setPlayer(mExoPlayer);
                // Prepare the MediaSource.
                String userAgent = Util.getUserAgent(getContext(), getString(R.string.app_name));
                MediaSource mediaSource = new ExtractorMediaSource(mediaUri, new DefaultDataSourceFactory(
                        getContext(), userAgent), new DefaultExtractorsFactory(), null, null);
                mExoPlayer.prepare(mediaSource);
                mExoPlayer.setPlayWhenReady(true);
            }
    }

    /**
     * Release ExoPlayer.
     */
    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    /**
     * Release the player when the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
    }
}