package com.example.simplecrud;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class CreateActivity extends AppCompatActivity {
    // Declaration
    ImageView uploadImage;
    EditText uploadUrl, uploadTitle, uploadDesc;
    Button saveButton, backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // Initialization
        uploadImage = findViewById(R.id.uploadImage);
        uploadUrl = findViewById(R.id.uploadUrl);
        uploadTitle = findViewById(R.id.uploadTitle);
        uploadDesc = findViewById(R.id.uploadDesc);
        saveButton = findViewById(R.id.saveButton);
        backButton = findViewById(R.id.backButton);

        // Event Listener
        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uploadUrl.getVisibility() == View.GONE) {
                    uploadUrl.setVisibility(View.VISIBLE);
                } else {
                    uploadUrl.setVisibility(View.GONE);
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}