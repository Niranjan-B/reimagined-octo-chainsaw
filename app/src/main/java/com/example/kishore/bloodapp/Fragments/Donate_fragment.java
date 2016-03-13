package com.example.kishore.bloodapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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


/**
 * A simple {@link Fragment} subclass.
 */
public class Donate_fragment extends DialogFragment {


    MaterialEditText mail_id;
    Spinner spinner_blood;
    Spinner spinner_area;


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
        ArrayAdapter<CharSequence> adapter_blood = ArrayAdapter.createFromResource(getActivity(), R.array.blood_groups, android.R.layout.simple_spinner_item);
        adapter_blood.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_blood.setAdapter(adapter_blood);
        spinner_blood.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String temp = (String) parent.getItemAtPosition(position);
                Log.d("temp", temp);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        ArrayAdapter<CharSequence> adapter_area = ArrayAdapter.createFromResource(getActivity(), R.array.Area, android.R.layout.simple_spinner_item);
        adapter_area.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_area.setAdapter(adapter_area);
        spinner_area.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String temp = (String) parent.getItemAtPosition(position);
                Log.d("temp", temp);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });

        return view;


    }


}
