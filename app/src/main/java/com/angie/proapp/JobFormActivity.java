package com.angie.proapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class JobFormActivity extends AppCompatActivity {

    ImageView imageAttachment;

    final int DOCUMENT_REQUEST_CODE = 172;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobform);

        Button btnPost = findViewById(R.id.btn_post);

        imageAttachment = findViewById(R.id.image_attachment);

        imageAttachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickAttachment = new Intent(Intent.ACTION_GET_CONTENT);
                pickAttachment.setType("application/pdf");
                String[] mimeTypes = {
                        "application/xls",
                        "application/pdf",
                        "application/docx",
                        "application/doc",
                };
                pickAttachment.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
                pickAttachment.addCategory(Intent.CATEGORY_OPENABLE);


                try {
                    startActivityForResult(pickAttachment, DOCUMENT_REQUEST_CODE);
                } catch (ActivityNotFoundException e) {

                    Toast.makeText(JobFormActivity.this, "Please Attach your File", Toast.LENGTH_SHORT).show();
                }
            }

        });



        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToDetails = new Intent(JobFormActivity.this,JobDetailsActivity.class);
                startActivity(goToDetails);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == DOCUMENT_REQUEST_CODE && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            imageAttachment.setImageDrawable(getResources().getDrawable(R.drawable.food));
        }

    }
}