package com.android.creamntreats;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.creamntreats.Fragment.RecipeDetailsFragment;

public class RecipeDetailsActivity extends AppCompatActivity {
    BaseRecipeLayout recipe;
    String recipeName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle selectedRecipeBundle = null;
        setContentView(R.layout.activity_recipe_details);
        if(savedInstanceState != null) {
            recipe = savedInstanceState.getParcelable(getString(R.string.selected_recipe_bundle));
            recipeName = savedInstanceState.getString(getString(R.string.selected_recipe_name));
        }else {
            selectedRecipeBundle = getIntent().getExtras();
            recipe = selectedRecipeBundle.getParcelable(getString(R.string.selected_recipe_bundle));
            recipeName = recipe.getName();
            Log.i("RecipeDetailsActiity ", "recipeName " + recipeName + " " + recipe.getId());
            Log.i("RecipeDetailsActiity ", "getServings " + recipe.getServings());
            RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();
            recipeDetailsFragment.setArguments(selectedRecipeBundle);
            // Add the fragment to its container using a FragmentManager and a Transaction
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container,recipeDetailsFragment)
                    .commit();
        }
        setActionBarTitle(recipeName);



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
    public void onSaveInstanceState(Bundle currentState) {
        super.onSaveInstanceState(currentState);
        currentState.putParcelable(getString(R.string.selected_recipe_bundle),recipe);
        currentState.putString(getString(R.string.selected_recipe_name),recipeName);
    }

}
