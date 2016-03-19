package com.example.kishore.bloodapp.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by NIRANJAN on 11-03-2016.
 * a ninja - prod :D
 */
public class RetrofitService {

    private static String signUpUrl = "http://www.ninja-root.site40.net/reimagined_octo_chainsaw/";

    public static Retrofit getRetrofitAdapter() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(signUpUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }
}
