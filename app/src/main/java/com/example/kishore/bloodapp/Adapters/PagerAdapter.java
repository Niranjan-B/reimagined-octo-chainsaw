package com.example.kishore.bloodapp.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kishore.bloodapp.Fragments.Donate_fragment;
import com.example.kishore.bloodapp.Fragments.Request_fragment;

/**
 * Created by kishore on 3/3/2016.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    public static final int pages = 2;
    public static String[] str = new String[]{"Request For Blood", "Donate Blood"};

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return Request_fragment.newInstance();
            case 1:
                return Donate_fragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return pages;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return str[position];
    }
}
