package com.example.kishore.bloodapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.kishore.bloodapp.Models.SignUpResponse;
import com.example.kishore.bloodapp.Services.RetrofitEndPoints;
import com.example.kishore.bloodapp.Services.RetrofitService;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.Button;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    MaterialEditText mailId, password;
    Button sign_in, sign_up;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mailId = (MaterialEditText) findViewById(R.id.edittext_mail_id);
        password = (MaterialEditText) findViewById(R.id.edittext_password);
        sign_in = (Button) findViewById(R.id.button_login);
        sign_up = (Button) findViewById(R.id.button_signup);
        progressDialog = new ProgressDialog(MainActivity.this);
        
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputCredentials()) {
                    loginWithServer();
                } else {
                    showToast("Oops! Both the fields are mandatory");
                }
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign_up = new Intent(MainActivity.this, Sign_up.class);
                startActivity(sign_up);
            }
        });
    }

    private void loginWithServer() {
        RetrofitEndPoints retrofitEndPoints = RetrofitService.getRetrofitAdapter().create(RetrofitEndPoints.class);

        showProgressDialog();

        Map<String, String> userCredentialsMap = new HashMap<>();
        userCredentialsMap.put("mail_id", mailId.getText().toString());
        userCredentialsMap.put("password", password.getText().toString());

        Call<SignUpResponse> callServer = retrofitEndPoints.login(userCredentialsMap);
        callServer.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                SignUpResponse signUpResponse = response.body();

                if(signUpResponse.getStatus()) {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    startActivity(intent);
                } else {
                    mailId.setError("double check your mail - id.");
                    password.setError("check your password as well.");
                }

                stopProgressDialog();
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                stopProgressDialog();
            }
        });
    }

    private void stopProgressDialog() {
        if(progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    private void showProgressDialog() {
        progressDialog.setMessage("Logging you in...");
        progressDialog.show();
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
    }

    private boolean validateInputCredentials() {
        String tempMailId = mailId.getText().toString();
        String tempPassword = password.getText().toString();

        if(tempMailId.isEmpty() || tempPassword.isEmpty()) {
            return false;
        }
        return true;
    }


}
