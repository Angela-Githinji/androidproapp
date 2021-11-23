package com.angie.proapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.angie.proapp.adapters.ProfessionAdapter;
import com.angie.proapp.adapters.ProfessionalAdapter;
import com.angie.proapp.models.Profession;
import com.angie.proapp.models.Professional;
import com.angie.proapp.utilities.PreferenceStorage;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    List<Professional> professionals = new ArrayList<>();
    ProfessionalAdapter professionalAdapter;

    List<Profession> professions = new ArrayList<>();
    ProfessionAdapter professionAdapter;

    TextView txthello;
    PreferenceStorage preferenceStorage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferenceStorage = new PreferenceStorage(this);

        txthello = findViewById(R.id.txt_hello);

        RecyclerView professionsRecyclerview = findViewById(R.id.profession_recyclerview);
        professionsRecyclerview.setLayoutManager(new GridLayoutManager(this,2));

        professionsRecyclerview.setNestedScrollingEnabled(true);
        professions.add(new Profession("Teacher"));
        professions.add(new Profession("Pilot"));
        professions.add(new Profession("Musician"));
        professions.add(new Profession("Jumper"));


        professionAdapter = new ProfessionAdapter(this,professions);
        professionsRecyclerview.setAdapter(professionAdapter);



        RecyclerView hiresRecyclerviews = findViewById(R.id.Hires_recyclerview);

        hiresRecyclerviews.setNestedScrollingEnabled(true);
        hiresRecyclerviews.setLayoutManager(new LinearLayoutManager(this));

        professionals.add(new Professional("Sam","sam@gmail.com","Engineer","0712345678"));
        professionals.add(new Professional("Angela","angela@gmail.com","Model","0776543218"));
        professionals.add(new Professional("Delvin","delvin@gmail.com","Musician","0723456781"));
        professionals.add(new Professional("Brian","brian@gmail.com","Actor","0787654321"));

        professionalAdapter = new ProfessionalAdapter(this,professionals);
        hiresRecyclerviews.setAdapter(professionalAdapter);

        Button btnAllProfessions = findViewById(R.id.btn_all_professions);

        btnAllProfessions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 Intent goToDetails
                        =new Intent(HomeActivity.this,ProDetailsActivity.class);
                startActivity(goToDetails);
//                finish();



            }

        });


    }

    @Override
    protected void onResume() {
        super.onResume();


        String name = preferenceStorage.getUserName();
        txthello.setText("Hello "+name);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int menu_item = item.getItemId();

        switch (menu_item) {

            case R.id.action_settings:
                Toast.makeText(HomeActivity.this, "Settings Clicked.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_logout:
               showAlert();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Don't Leave Me")
                .setMessage("Are You Sure You Want To Go?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        preferenceStorage.setUserLoggedIn(false);
                        Intent intent = new Intent(HomeActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Stay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                      dialog.dismiss();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}