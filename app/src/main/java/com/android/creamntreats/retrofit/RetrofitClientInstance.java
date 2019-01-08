package com.android.creamntreats.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sangeetha_gsk on 1/5/19.
 */

public class RetrofitClientInstance {

    static final String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
                retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
    return retrofit;
    }


}
