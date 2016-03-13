package com.example.kishore.bloodapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.kishore.bloodapp.R;
import com.rengwuxian.materialedittext.MaterialEditText;


public class Request_fragment extends Fragment {
    Spinner spinner_blood;
    Spinner spinner_area;

    public Request_fragment() {
        // Required empty public constructor
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

        ArrayAdapter<CharSequence> adapter_blood = ArrayAdapter.createFromResource(getActivity(), R.array.blood_groups, android.R.layout.simple_spinner_item);
        adapter_blood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_blood.setAdapter(adapter_blood);
        spinner_blood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String temp = (String) parent.getItemAtPosition(position);
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
                String temp=(String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return view;
    }


}
