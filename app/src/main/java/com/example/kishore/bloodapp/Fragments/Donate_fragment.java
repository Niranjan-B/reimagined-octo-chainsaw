package com.example.kishore.bloodapp.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kishore.bloodapp.Models.SignUpResponse;
import com.example.kishore.bloodapp.R;
import com.example.kishore.bloodapp.Services.RetrofitEndPoints;
import com.example.kishore.bloodapp.Services.RetrofitService;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * A simple {@link Fragment} subclass.
 */
public class Donate_fragment extends DialogFragment {


    MaterialEditText mail_id;
    Spinner spinner_blood;
    Spinner spinner_area;
    Button donateRequestButton;
    String area, bloodGroupType;
    ProgressDialog progressDialog;


    public Donate_fragment() {
        // Required empty public constructor
    }

    public static Donate_fragment newInstance() {
        Donate_fragment fragment = new Donate_fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_donate_fragment, container, false);

        mail_id = (MaterialEditText) view.findViewById(R.id.edittext_email_req);
        spinner_blood = (Spinner) view.findViewById(R.id.spinner_blood);
        spinner_area = (Spinner) view.findViewById(R.id.spinner_area);
        donateRequestButton = (Button) view.findViewById(R.id.button_request);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Sending your request...");

        ArrayAdapter<CharSequence> adapter_blood = ArrayAdapter.createFromResource(getActivity(), R.array.blood_groups, android.R.layout.simple_spinner_item);
        adapter_blood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_blood.setAdapter(adapter_blood);
        spinner_blood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                bloodGroupType = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<CharSequence> adapter_area = ArrayAdapter.createFromResource(getActivity(), R.array.Area, android.R.layout.simple_spinner_item);
        adapter_area.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_area.setAdapter(adapter_area);
        spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                area = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        donateRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateUser()) {
                    fireDonateBloodService();
                }
            }
        });

        return view;
    }

    private void fireDonateBloodService() {

        showProgressDialog();

        Map<String, String> tempMap = new HashMap<>();
        tempMap.put("mail_id", mail_id.getText().toString());
        tempMap.put("locality", area);
        tempMap.put("schedule", bloodGroupType);

        RetrofitEndPoints retrofitEndPoints = RetrofitService.getRetrofitAdapter().create(RetrofitEndPoints.class);
        Call<SignUpResponse> apiCall = retrofitEndPoints.requestToDonate(tempMap);
        apiCall.enqueue(new Callback<SignUpResponse>() {
            @Override
            public void onResponse(Call<SignUpResponse> call, Response<SignUpResponse> response) {
                if (response.isSuccess()) {
                    showToast("Your request has been successfully recorded!");
                    stopProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<SignUpResponse> call, Throwable t) {
                showToast("Oops! Something went wrong.");
                stopProgressDialog();
            }
        });
    }

    private Boolean validateUser() {
        return (mail_id.getText().toString().trim().contains(""));
    }

    private void showProgressDialog() {
        progressDialog.show();
    }

    private void stopProgressDialog() {
        if(progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

    private void showToast(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
