package com.android.creamntreats;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.android.creamntreats.Fragment.RecipeStepDetailsFragment;
import com.android.creamntreats.recipe.RecipeSteps;

import java.util.ArrayList;
import java.util.List;

public class RecipeStepDetailsActivity extends AppCompatActivity {
    List<RecipeSteps> recipeSteps;
    int stepIndex;
    String recipeName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_step_details);

        setActionBarTitle(recipeName);

        if(savedInstanceState == null) {

            RecipeStepDetailsFragment recipeStepDetailsFragment = new RecipeStepDetailsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();

            Bundle selectedRecipeBundle = getIntent().getExtras();
            recipeSteps = selectedRecipeBundle.getParcelableArrayList(getString(R.string.selected_recipe_step_bundle));
            stepIndex = selectedRecipeBundle.getInt(getString(R.string.selected_recipe_step_index));
            recipeName = selectedRecipeBundle.getString(getString(R.string.selected_recipe_name));
            recipeStepDetailsFragment.setArguments(selectedRecipeBundle);

            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container2, recipeStepDetailsFragment)
                    .commit();


        } else{
            recipeSteps =savedInstanceState.getParcelableArrayList(getString(R.string.selected_recipe_step_bundle));
            stepIndex=savedInstanceState.getInt(getString(R.string.selected_recipe_step_index));
            recipeName=savedInstanceState.getString(getString(R.string.selected_recipe_name));
        }
    }

    public void setActionBarTitle(String title){
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(getString(R.string.selected_recipe_name),recipeName);
        outState.putParcelableArrayList(getString(R.string.selected_recipe_step_bundle), (ArrayList<RecipeSteps>)recipeSteps);
        outState.putInt(getString(R.string.selected_recipe_step_index),stepIndex);

    }
}
