package com.android.creamntreats.recipe;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by sangeetha_gsk on 1/5/19.
 */

public class RecipeSteps implements Parcelable {

    private Integer id;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    public Integer getId() {
        return id;
    }

    public void setStepId(Integer id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        if(id == null){
            dest.writeInt(0);
        }else {
            dest.writeInt(this.id);
        }

        dest.writeString(this.shortDescription);
        dest.writeString(this.description);
        dest.writeString(this.videoURL);

        if(thumbnailURL == null){
            dest.writeString("");
        }else {
            dest.writeString(this.thumbnailURL);
        }

    }

    protected RecipeSteps(Parcel in) {

        this.id = in.readInt();
        this.shortDescription = in.readString();
        this.description = in.readString();
        this.videoURL = in.readString();
        this.thumbnailURL = in.readString();
    }

    public static final Creator<RecipeSteps> CREATOR = new Creator<RecipeSteps>() {
        @Override
        public RecipeSteps createFromParcel(Parcel source) {
            return new RecipeSteps(source);
        }

        @Override
        public RecipeSteps[] newArray(int size) {
            return new RecipeSteps[size];
        }
    };


}
