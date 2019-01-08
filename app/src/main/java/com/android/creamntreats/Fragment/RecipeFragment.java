package com.android.creamntreats.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.creamntreats.BaseRecipeLayout;
import com.android.creamntreats.R;
import com.android.creamntreats.adapter.RecipeAdapter;
import com.android.creamntreats.retrofit.GetRecipeService;
import com.android.creamntreats.retrofit.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecipeFragment extends Fragment {


    public RecipeFragment() {
        // Required empty public constructor
    }
    /**
     * Inflates the fragment layout file and sets the correct resource for the image to display
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("RecipeFragment", "onCreateView: ");
        // Load the saved state (the list of images and list index) if there is one
        if (savedInstanceState != null) {

        }
        // Inflate the recipe_fragment  layout
        View rootView = inflater.inflate(R.layout.fragment_recipe, container, false);
        RecyclerView recyclerView;
        recyclerView=(RecyclerView)  rootView.findViewById(R.id.rv_recipe);


        final RecipeAdapter recipesAdapter =new RecipeAdapter(getContext());
        recyclerView.setAdapter(recipesAdapter);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        GetRecipeService getRecipeService = RetrofitClientInstance.getRetrofitInstance().create(GetRecipeService.class);
        Call<ArrayList<BaseRecipeLayout>> recipeCall = getRecipeService.getRecipe();
        recipeCall.enqueue(new Callback<ArrayList<BaseRecipeLayout>>() {
            @Override
            public void onResponse(Call<ArrayList<BaseRecipeLayout>> call, Response<ArrayList<BaseRecipeLayout>> response) {

                Integer statusCode = response.code();
                Log.v("status code: ", statusCode.toString());

                ArrayList<BaseRecipeLayout> recipeDetails = response.body();
                Log.i("Fragment class", "onResponse: ");
                Bundle recipesBundle = new Bundle();
                recipesBundle.putParcelableArrayList("ALL_RECIPES", recipeDetails);
                Log.i("Fragment class", "Recipe details: "+recipeDetails.get(1).getName());
                Log.i("Fragment class", "Recipe details: "+recipeDetails.get(2).getName());

                recipesAdapter.setBaseRecipeData(recipeDetails);
            }

            @Override
            public void onFailure(Call<ArrayList<BaseRecipeLayout>> call, Throwable t) {
                Log.v("Failure to get the json response object : ", t.toString());

            }
        });





        return rootView;
    }

}
