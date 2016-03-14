package com.example.kishore.bloodapp.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kishore.bloodapp.Adapters.RecyclerViewDonorListAdapter;
import com.example.kishore.bloodapp.Models.DonorsCredentials;
import com.example.kishore.bloodapp.Models.DonorsList;
import com.example.kishore.bloodapp.R;
import com.example.kishore.bloodapp.RecyclerItemClickListener;
import com.example.kishore.bloodapp.Services.RetrofitEndPoints;
import com.example.kishore.bloodapp.Services.RetrofitService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Request_fragment extends Fragment {
    Spinner spinner_blood;
    Spinner spinner_area;
    String bloodType, area;
    LinearLayout textViewHolder, bloodSpinnerHolder, areaSpinnerHolder;
    Button searchDonors, backToRequestButton;
    RecyclerView donorsList;
    ProgressDialog progressDialog;
    ArrayList<DonorsCredentials> donorsCredentialsArrayList;
    RecyclerViewDonorListAdapter recyclerViewDonorListAdapter;

    public Request_fragment() {
    }

    public static Request_fragment newInstance() {
        Request_fragment fragment = new Request_fragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_request_fragment, container, false);
        spinner_blood = (Spinner) view.findViewById(R.id.spinner);
        spinner_area = (Spinner) view.findViewById(R.id.spinner_area);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Fetching donors.....");

        textViewHolder = (LinearLayout) view.findViewById(R.id.textContainerLinearLayout);
        bloodSpinnerHolder = (LinearLayout) view.findViewById(R.id.linear1);
        areaSpinnerHolder  = (LinearLayout) view.findViewById(R.id.linear2);
        searchDonors = (Button) view.findViewById(R.id.searchDonorsButton);
        backToRequestButton = (Button) view.findViewById(R.id.backToRequestButton);

        donorsCredentialsArrayList = new ArrayList<>();

        donorsList = (RecyclerView) view.findViewById(R.id.donorsList);
        recyclerViewDonorListAdapter = new RecyclerViewDonorListAdapter(getActivity());
        donorsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        donorsList.setAdapter(recyclerViewDonorListAdapter);

        ArrayAdapter<CharSequence> adapter_blood = ArrayAdapter.createFromResource(getActivity(), R.array.blood_groups, android.R.layout.simple_spinner_item);
        adapter_blood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_blood.setAdapter(adapter_blood);
        spinner_blood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                bloodType = (String) parent.getItemAtPosition(position);
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

        searchDonors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideViews(new View[]{textViewHolder, bloodSpinnerHolder, areaSpinnerHolder, searchDonors});
                fireRequest();
            }
        });

        backToRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showViews(new View[]{textViewHolder, bloodSpinnerHolder, areaSpinnerHolder, searchDonors});
                hideViews(new View[]{donorsList, backToRequestButton});
            }
        });

        donorsList.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("recycler", "hell0 = " + donorsCredentialsArrayList.get(position).getAddress());
            }
        }));

        return view;
    }

    private void fireRequest() {

        showProgressDialog();

        Map<String, String> tempCredentialsMap = new HashMap<>();
        tempCredentialsMap.put("blood_group", bloodType);
        tempCredentialsMap.put("locality", area);

        RetrofitEndPoints endPoints = RetrofitService.getRetrofitAdapter().create(RetrofitEndPoints.class);
        Call<DonorsList> apiCall = endPoints.fetchDonors(tempCredentialsMap);
        apiCall.enqueue(new Callback<DonorsList>() {
            @Override
            public void onResponse(Call<DonorsList> call, Response<DonorsList> response) {
                DonorsList tempList = response.body();
                donorsCredentialsArrayList = tempList.getDonors();
                if(tempList.getStatus() && (!donorsCredentialsArrayList.isEmpty())) {
                    recyclerViewDonorListAdapter.updateRecyclerView(donorsCredentialsArrayList);
                    hideViews(new View[]{textViewHolder, bloodSpinnerHolder, areaSpinnerHolder, searchDonors});
                    showViews(new View[]{backToRequestButton, donorsList});
                } else {
                    Toast.makeText(getActivity(), "Oops! Looks like there are no donors!", Toast.LENGTH_SHORT).show();
                    showViews(new View[]{textViewHolder, bloodSpinnerHolder, areaSpinnerHolder, searchDonors});
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<DonorsList> call, Throwable t) {
                Log.d("retrofit", "inside on failure");
                showViews(new View[]{textViewHolder, bloodSpinnerHolder, areaSpinnerHolder, searchDonors});
                hideProgressDialog();
            }
        });
    }


    public void hideViews(View[] views) {
        for(View view : views) {
            if(view.getVisibility() == View.VISIBLE) {
                view.setVisibility(View.GONE);
            }
        }
    }

    public void showViews(View[] views) {
        for(View view : views) {
            if(view.getVisibility() == View.GONE) {
                view.setVisibility(View.VISIBLE);
            }
        }
    }

    public void showProgressDialog() {
        progressDialog.show();
    }

    public void hideProgressDialog() {
        if(progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }
}
