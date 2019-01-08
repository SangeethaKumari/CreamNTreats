package com.android.creamntreats.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.creamntreats.BaseRecipeLayout;
import com.android.creamntreats.R;
import com.android.creamntreats.RecipeStepDetailsActivity;
import com.android.creamntreats.recipe.RecipeSteps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sangeetha_gsk on 1/5/19.
 */

public class RecipeDetailsAdapter extends RecyclerView.Adapter<RecipeDetailsAdapter.RecipeDetailsHolder> {

    // Keeps track of the context and list of images to display
    private Context mContext;
    BaseRecipeLayout recipe;
    List<RecipeSteps> recipeSteps ;

    public RecipeDetailsAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecipeDetailsAdapter.RecipeDetailsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.recipe_detail_short_description_cardview,parent,false);
        return new RecipeDetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeDetailsAdapter.RecipeDetailsHolder holder, final int position) {

        String recipeShortDescription = recipeSteps.get(position).getShortDescription();

        holder.recipe_short_description.
                setText(recipeSteps.get(position).getId() + "." + recipeShortDescription);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selectedRecipeBundle = new Intent(mContext,RecipeStepDetailsActivity.class);
                RecipeSteps recipeStep = recipeSteps.get(position);

                selectedRecipeBundle.putParcelableArrayListExtra(mContext.getString(R.string.selected_recipe_step_bundle),
                        (ArrayList<RecipeSteps>)recipeSteps);
                selectedRecipeBundle.putExtra(mContext.getString(R.string.selected_recipe_step_index),position);
                selectedRecipeBundle.putExtra(mContext.getString(R.string.selected_recipe_name),recipe.getName());

                mContext.startActivity(selectedRecipeBundle);

            }
        });


    }

    @Override
    public int getItemCount() {
        if(recipeSteps !=null){
            return recipeSteps.size();
        }
        return 0;
    }

    public void setRecipeDetails(BaseRecipeLayout recipe) {
        this.recipe = recipe;
        this.recipeSteps = recipe.getSteps();
        notifyDataSetChanged();
    }

    class RecipeDetailsHolder extends RecyclerView.ViewHolder
             implements View.OnClickListener{

        TextView recipe_short_description;
        CardView cardView;

         public RecipeDetailsHolder(View itemView) {
             super(itemView);
             recipe_short_description = itemView.findViewById(R.id.recipe_short_description);

             cardView = itemView.findViewById(R.id.cardview_short_description_id);

         }

         @Override
         public void onClick(View view) {

         }
    }
}
