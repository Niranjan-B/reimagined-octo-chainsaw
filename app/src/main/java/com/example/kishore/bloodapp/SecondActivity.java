package com.example.kishore.bloodapp;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.kishore.bloodapp.Adapters.PagerAdapter;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class SecondActivity extends AppCompatActivity implements MaterialTabListener{
    MaterialTabHost mt;
    ViewPager pager;
    public static String[] str= new String[]{"Request For Blood","Dontae Blood"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
         pager= (ViewPager) findViewById(R.id.pager);
         mt= (MaterialTabHost) findViewById(R.id.materialTabHost);
        Toolbar tb = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(tb);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setSupportActionBar(tb);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });



        PagerAdapter pagerAdapter= new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                mt.setSelectedNavigationItem(position);
            }
        });
        for (int i = 0; i < 2; i++) {
            mt.addTab(
                    mt.newTab()
                            .setText(str[i])
                            .setTabListener(this)
            );
        }


    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        pager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }
}


