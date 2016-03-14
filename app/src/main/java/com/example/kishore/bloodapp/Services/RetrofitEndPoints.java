package com.example.kishore.bloodapp.Services;

import com.example.kishore.bloodapp.Models.DonorsList;
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

    // logging in end-point
    @GET("login.php")
    Call<SignUpResponse> login(@QueryMap Map<String, String> loginCredentials);

    //fetching donors end point
    @GET("get_donors.php")
    Call<DonorsList> fetchDonors(@QueryMap Map<String, String> fetchingCriteria);

    //request to donate end point
    @GET("donate.php")
    Call<SignUpResponse> requestToDonate(@QueryMap Map<String, String> donateCredentials);
}
