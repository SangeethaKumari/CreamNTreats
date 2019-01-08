package com.android.creamntreats.retrofit;

import com.android.creamntreats.BaseRecipeLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sangeetha_gsk on 1/5/19.
 */

public interface GetRecipeService {
    @GET("/topher/2017/May/59121517_baking/baking.json")
    Call<ArrayList<BaseRecipeLayout>> getRecipe();
}
