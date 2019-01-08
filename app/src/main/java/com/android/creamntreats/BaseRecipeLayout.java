package com.android.creamntreats;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.android.creamntreats.recipe.RecipeIngredients;
import com.android.creamntreats.recipe.RecipeSteps;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sangeetha_gsk on 1/5/19.
 */

public class BaseRecipeLayout implements Parcelable {


    private Integer id;
    private String name;
    private List<RecipeIngredients> ingredients = null;
    private List<RecipeSteps> steps = null;
    private Integer servings;
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<RecipeIngredients> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredients> ingredients) {
        this.ingredients = ingredients;
    }

    public List<RecipeSteps> getSteps() {
        return steps;
    }

    public void setSteps(List<RecipeSteps> steps) {
        this.steps = steps;
    }

    public Integer getServings() {
        return servings;
    }

    public void setServings(Integer servings) {
        this.servings = servings;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if(id == null){
            dest.writeInt(0);
        }else {
            dest.writeInt(this.id);
        }
        Log.i("Layout", "writeToParcel: "+this.id + " name "+this.name);
        dest.writeString(this.name);
        dest.writeTypedList(this.ingredients);
        dest.writeTypedList(this.steps);
        dest.writeInt(this.servings);
        dest.writeString(this.image);

    }

    protected BaseRecipeLayout(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.ingredients = new ArrayList<>();
        in.readTypedList(this.ingredients,RecipeIngredients.CREATOR);
        this.steps = new ArrayList<>();
        in.readTypedList(steps, RecipeSteps.CREATOR);
        this.servings = in.readInt();

        this.image = in.readString();



        Log.i("Layout", "BaseRecipeLayout: "+this.id + " name "+this.name);

    }

    public static final Creator<BaseRecipeLayout> CREATOR = new Creator<BaseRecipeLayout>() {
        @Override
        public BaseRecipeLayout createFromParcel(Parcel source) {
            return new BaseRecipeLayout(source);
        }

        @Override
        public BaseRecipeLayout[] newArray(int size) {
            return new BaseRecipeLayout[size];
        }
    };


    @Override
    public String toString() {
        return "BaseRecipeLayout{" +
                "image='" + image + '\'' +
                ", servings=" + servings +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                ", id=" + id +
                ", steps=" + steps +
                '}';
    }
}
