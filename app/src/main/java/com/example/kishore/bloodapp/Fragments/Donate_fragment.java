package com.example.kishore.bloodapp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kishore.bloodapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Donate_fragment extends Fragment {

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
        return inflater.inflate(R.layout.fragment_donate_fragment, container, false);
    }

}
