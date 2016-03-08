package com.example.kishore.bloodapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.widget.Button;

public class MainActivity extends AppCompatActivity {
    MaterialEditText maid_id, password;
    Button sign_in, sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        maid_id = (MaterialEditText) findViewById(R.id.edittext_mail_id);
        password = (MaterialEditText) findViewById(R.id.edittext_password);
        sign_in = (Button) findViewById(R.id.button_login);
        sign_up = (Button) findViewById(R.id.button_signup);
        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent= new Intent(MainActivity.this,SecondActivity.class);

                Toast.makeText(MainActivity.this, maid_id.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sign_up= new Intent(MainActivity.this, Sign_up.class);
                startActivity(sign_up);
                Toast.makeText(MainActivity.this, password.getText().toString(), Toast.LENGTH_LONG).show();

            }
        });

    }
}
