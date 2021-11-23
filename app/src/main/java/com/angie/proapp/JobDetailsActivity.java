package com.angie.proapp;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresPermission;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.angie.proapp.databinding.ActivityJobDetailsBinding;

public class JobDetailsActivity extends AppCompatActivity {

    private ActivityJobDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityJobDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_job_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menu_item = item.getItemId();

        switch (menu_item){

            case R.id.action_settings:
                Toast.makeText(JobDetailsActivity.this, "You reached settings.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_favorite:
                Toast.makeText(JobDetailsActivity.this, "I lOVE you too.", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action_mail:
                Intent sendmail = new Intent(Intent.ACTION_SEND);
                sendmail.putExtra(Intent.EXTRA_SUBJECT,"I Need a Job");
                sendmail.setType("message/rfc822");
                String [] addresses = {"kenya@bolt.eu"};
                sendmail.putExtra(Intent.EXTRA_EMAIL, addresses);
                startActivity(sendmail);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}