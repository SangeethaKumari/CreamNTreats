package com.android.creamntreats.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.creamntreats.BaseRecipeLayout;
import com.android.creamntreats.R;
import com.android.creamntreats.RecipeDetailsActivity;
import com.android.creamntreats.adapter.RecipeDetailsAdapter;
import com.android.creamntreats.recipe.RecipeIngredients;

import java.util.List;


public class RecipeDetailsFragment extends Fragment {
    BaseRecipeLayout recipe;
    TextView recipe_ingredients;

    public RecipeDetailsFragment(){

    }


    /**
     * Inflates the fragment layout file and sets the correct resource for the image to display
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        StringBuffer ingredient = new StringBuffer("");
        // Load the saved state (the list of images and list index) if there is one
        if (savedInstanceState != null) {

        }

        recipe =getArguments().getParcelable(getString(R.string.selected_recipe_bundle));
        String recipeName = getArguments().getString(getString(R.string.selected_recipe_name));

        ((RecipeDetailsActivity) getActivity()).setActionBarTitle(recipeName);

        List ingredientsList = recipe.getIngredients();
        for (int i = 0; i < ingredientsList.size(); i++) {
            System.out.println(ingredientsList.get(i));
            RecipeIngredients recipeIngredients = (RecipeIngredients) ingredientsList.get(i);
            String ingredientLabel = recipeIngredients.getIngredient();
            ingredientLabel = ingredientLabel.substring(0, 1).toUpperCase() + ingredientLabel.substring(1);

            ingredient.append((i+1)+ ". "+ingredientLabel + " "+recipeIngredients.getQuantity() +
                    " "+recipeIngredients.getMeasure());
            if(i <= ingredientsList.size()) {
                ingredient.append("\n");
            }
        }

        // Inflate the fragment_recipe_details fragment layout
        View rootView = inflater.inflate(R.layout.fragment_recipe_details, container, false);
        RecyclerView recyclerView;
        recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_recipe_details);

        recipe_ingredients = rootView.findViewById(R.id.recipe_ingredients);
        recipe_ingredients.setText(ingredient);


        final RecipeDetailsAdapter recipeDetailsAdapter = new RecipeDetailsAdapter(getContext());
        recipeDetailsAdapter.setRecipeDetails(recipe);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(recipeDetailsAdapter);

        return rootView;
    }

    }
