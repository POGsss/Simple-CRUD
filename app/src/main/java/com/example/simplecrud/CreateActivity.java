package com.example.simplecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
                insertData();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void insertData() {
        Map<String, Object> map = new HashMap<>();
        map.put("imageUrl", uploadUrl.getText().toString());
        map.put("title", uploadTitle.getText().toString());
        map.put("description", uploadDesc.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("data").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(CreateActivity.this, "Data Created", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CreateActivity.this, "Data Not Created", Toast.LENGTH_SHORT).show();
                    }
                });

        uploadUrl.setText("");
        uploadTitle.setText("");
        uploadDesc.setText("");
    }
}