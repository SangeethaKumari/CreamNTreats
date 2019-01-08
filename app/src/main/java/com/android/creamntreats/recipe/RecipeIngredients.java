package com.android.creamntreats.recipe;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sangeetha_gsk on 1/5/19.
 */

public class RecipeIngredients implements Parcelable{


    private Double quantity;
    private String measure;
    private String ingredient;

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeDouble(this.quantity);
        dest.writeString(this.ingredient);
        dest.writeString(this.measure);

    }

    protected RecipeIngredients(Parcel in) {

        this.quantity = in.readDouble();
        this.ingredient = in.readString();
        this.measure = in.readString();
    }

    public static final Creator<RecipeIngredients> CREATOR = new Creator<RecipeIngredients>() {
        @Override
        public RecipeIngredients createFromParcel(Parcel source) {
            return new RecipeIngredients(source);
        }

        @Override
        public RecipeIngredients[] newArray(int size) {
            return new RecipeIngredients[size];
        }
    };
}
