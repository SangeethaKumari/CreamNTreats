package com.android.creamntreats.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.creamntreats.BaseRecipeLayout;
import com.android.creamntreats.R;
import com.android.creamntreats.RecipeDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by sangeetha_gsk on 1/5/19.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder>{

    // Keeps track of the context and list of recipes to display
    private Context mContext;
    private ArrayList<BaseRecipeLayout> baseRecipeDataList;

    public RecipeAdapter(Context context) {
        this.mContext = context;
    }
    /**
     * When  recipe data changes, this method updates the list of movies
     * and notifies the adapter to use the new values added
     */
    public void setBaseRecipeData(ArrayList<BaseRecipeLayout> recipeData) {
        baseRecipeDataList = recipeData;
        notifyDataSetChanged();
    }

    @Override
    public RecipeAdapter.RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("RecipeAdapter", "onCreateViewHolder: ");

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.recipe_cardview,parent,false);
        return new RecipeHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.RecipeHolder holder, final int position) {
        Log.i("RecipeAdapter", "onBindViewHolder: "+baseRecipeDataList.get(position).getName());
        Log.i("RecipeAdapter", "onBindViewHolder:getRecipeId "+baseRecipeDataList.get(position).getId());
        Log.i("RecipeAdapter", "onBindViewHolder:getServings "+baseRecipeDataList.get(position).getServings());

        holder.recipe_title.setText(baseRecipeDataList.get(position).getName());
        holder.recipe_serving.setText(String.valueOf(baseRecipeDataList.get(position).getServings()));
        String imageUrl = baseRecipeDataList.get(position).getImage();
        Log.i("RecipeAdapter", "imageUrl: " +imageUrl);

        if (imageUrl!=null& !"".equalsIgnoreCase(imageUrl)) {
            Log.i("RecipeAdapter", "imageUrl is not null: " +imageUrl);

            Uri builtUri = Uri.parse(imageUrl).buildUpon().build();
            Picasso.with(mContext).load(builtUri).into(holder.recipe_image_id);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,RecipeDetailsActivity.class);
                BaseRecipeLayout selectedRecipe = baseRecipeDataList.get(position);
                intent.putExtra(mContext.getString(R.string.selected_recipe_bundle),selectedRecipe);
                intent.putExtra(mContext.getString(R.string.selected_recipe_name),selectedRecipe.getName());
                // start the activity
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(baseRecipeDataList !=null){
            return baseRecipeDataList.size();
        }
        return 0;
    }

    class RecipeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        ImageView recipe_image_id ;
        TextView recipe_title;
        TextView recipe_serving;
        CardView cardView;

        public RecipeHolder(View itemView) {
            super(itemView);
            recipe_image_id = itemView.findViewById(R.id.recipe_image_id);
            recipe_title   = itemView.findViewById(R.id.recipe_title) ;
            recipe_serving   = itemView.findViewById(R.id.recipe_serving) ;
            cardView = itemView.findViewById(R.id.cardview_id);

        }

        @Override
        public void onClick(View view) {

        }
    }
}
