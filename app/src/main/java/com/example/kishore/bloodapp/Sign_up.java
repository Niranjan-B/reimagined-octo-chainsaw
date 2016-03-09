package com.example.kishore.bloodapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.Button;


public class Sign_up extends AppCompatActivity {
    MaterialEditText editText_name,editText_mail,editText_password,editText_repassword;
    Spinner blood_group;
    String blood_group_text, name, mail,password,repassword;
    Button sign_up1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText_name= (MaterialEditText) findViewById(R.id.edittext_name);
        editText_mail= (MaterialEditText) findViewById(R.id.edittext_mail);
         mail=editText_mail.getText().toString();
        editText_password= (MaterialEditText) findViewById(R.id.edittext_password);
         password=editText_password.getText().toString();
        editText_repassword= (MaterialEditText) findViewById(R.id.edittext_re_password);
        repassword=editText_repassword.getText().toString();
        blood_group= (Spinner) findViewById(R.id.blood_spinner);
        sign_up1= (Button) findViewById(R.id.button_signup1);
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
                // TODO Auto-generated method stub
            }
        });
        sign_up1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sign_up.this,editText_name.getText().toString(),Toast.LENGTH_LONG).show();
            }
        });

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return true;
    }

}
