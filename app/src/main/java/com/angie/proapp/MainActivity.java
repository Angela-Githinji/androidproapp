package com.angie.proapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.angie.proapp.utilities.PreferenceStorage;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
int count = 0;
    PreferenceStorage preferenceStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferenceStorage = new PreferenceStorage(this);

        Button  btnMain  = findViewById(R.id.btn_main);
    Button  btnCreateProf = findViewById(R.id.btn_create_prof);

        if (preferenceStorage.isLoggedIn()){
            btnCreateProf.setVisibility(View.GONE);
        }

    btnMain.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent goToHome  = new Intent(MainActivity.this,HomeActivity.class);


            startActivity(goToHome);
        }
    });


    btnCreateProf.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            Intent goToAuth  = new Intent(MainActivity.this,AuthenticationActivity.class);


            startActivity(goToAuth);
        }
    });

    }

}