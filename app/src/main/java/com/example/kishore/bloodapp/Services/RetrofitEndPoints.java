package com.example.kishore.bloodapp.Services;

import com.example.kishore.bloodapp.Models.SignUpResponse;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by NIRANJAN on 11-03-2016.
 */
public interface RetrofitEndPoints {

    // used for signing - up....
    @GET("insert_data.php")
    Call<SignUpResponse> signUp(@QueryMap Map<String, String> userCredentials);

    @GET("login.php")
    Call<SignUpResponse> login(@QueryMap Map<String, String> loginCredentials);

}
