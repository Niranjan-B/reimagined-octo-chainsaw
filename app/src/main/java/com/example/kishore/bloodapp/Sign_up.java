package com.example.kishore.bloodapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kishore.bloodapp.Models.SignUpResponse;
import com.example.kishore.bloodapp.Services.RetrofitService;
import com.example.kishore.bloodapp.Services.RetrofitEndPoints;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.Button;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class Sign_up extends AppCompatActivity {
    MaterialEditText editText_name, editText_mail, editText_password, editText_repassword;
    Spinner blood_group, spinner_area;
    String blood_group_text, bloodDonorArea;
    Button sign_up1;
    Map<String, String> userInputs;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException{
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_sign_up);


        progressDialog = new ProgressDialog(Sign_up.this);
        userInputs = new HashMap<>();
        editText_name = (MaterialEditText) findViewById(R.id.edittext_name);
        editText_mail = (MaterialEditText) findViewById(R.id.edittext_mail);
        editText_password = (MaterialEditText) findViewById(R.id.edittext_password);
        editText_repassword = (MaterialEditText) findViewById(R.id.edittext_re_password);
        blood_group = (Spinner) findViewById(R.id.blood_spinner);
        spinner_area = (Spinner) findViewById(R.id.spinner_area);
        sign_up1 = (Button) findViewById(R.id.button_signup1);

        blood_group_text = null;

        ArrayAdapter<CharSequence> adapter_blood = ArrayAdapter.createFromResource(Sign_up.this, R.array.blood_groups, android.R.layout.simple_spinner_item);
        adapter_blood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        blood_group.setAdapter(adapter_blood);
        blood_group.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                blood_group_text = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter_area = ArrayAdapter.createFromResource(Sign_up.this, R.array.Area, android.R.layout.simple_spinner_item);
        adapter_area.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_area.setAdapter(adapter_area);
        spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                bloodDonorArea = (String) parent.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sign_up1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkForValidCredentials()) {
                    sendSignUpRequest();
                } else {
                    Toast.makeText(Sign_up.this, "All the fields are mandatory!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

    private boolean checkForValidCredentials() {
        String mail = editText_mail.getText().toString();
        String password = editText_password.getText().toString();
        String repassword = editText_repassword.getText().toString();
        String name = editText_name.getText().toString();

        if(!(mail.trim().equals("") && password.trim().equals("") && repassword.trim().equals("") && name.trim().equals(""))) {
            if(password.contentEquals(repassword)) {
                userInputs.put("name", name);
                userInputs.put("mail_id", mail);
                userInputs.put("password", password);
                userInputs.put("blood_group", blood_group_text);
                userInputs.put("locality", bloodDonorArea);
                return true;
            }
        }
        return false;
    }

    private void sendSignUpRequest() {
        RetrofitEndPoints signUpApiService = RetrofitService.getRetrofitAdapter().create(RetrofitEndPoints.class);
        Call<SignUpResponse> apiCall = signUpApiService.signUp(userInputs);
        startProgressBar();
        apiCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if(response.isSuccess()) {
                    SignUpResponse signUpResponse = response.body();
                    if(signUpResponse.getStatus()) {
                        showToast("Signed up successfully!");
                    } else {
                        showToast("Failed to register!!");
                    }
                }
                stopProgressBar();
                clearTextFields();
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                showToast("Failed to register!!");
                stopProgressBar();
                clearTextFields();
            }
        });
    }

    private void stopProgressBar() {
        if(progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    private void startProgressBar() {
        progressDialog.setMessage("Signing you up....");
        progressDialog.show();
    }

    public void showToast(String message) {
        Toast.makeText(Sign_up.this, message, Toast.LENGTH_LONG).show();
    }

    public void clearTextFields() {
        editText_mail.setText("");
        editText_name.setText("");
        editText_password.setText("");
        editText_repassword.setText("");
    }

}
