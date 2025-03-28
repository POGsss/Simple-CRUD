package com.example.simplecrud;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class UpdateActivity extends AppCompatActivity {
    // Declaration
    EditText updateUrl, updateTitle, updateDesc;
    Button cancelButton, updateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        // Initialization
        updateUrl = findViewById(R.id.updateUrl);
        updateTitle = findViewById(R.id.updateTitle);
        updateDesc = findViewById(R.id.updateDesc);
        cancelButton = findViewById(R.id.cancelButton);
        updateButton = findViewById(R.id.updateButton);

        // Getting Intent Extra
        String imageUrl = getIntent().getStringExtra("imageUrl");
        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String itemKey = getIntent().getStringExtra("itemKey").toString();

        // Populating Each Edit Text
        updateUrl.setText(imageUrl);
        updateTitle.setText(title);
        updateDesc.setText(description);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> map = new HashMap<>();
                map.put("imageUrl", updateUrl.getText().toString());
                map.put("title", updateTitle.getText().toString());
                map.put("description", updateDesc.getText().toString());

                FirebaseDatabase.getInstance().getReference()
                        .child("data")
                        .child(itemKey)
                        .updateChildren(map)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(UpdateActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UpdateActivity.this, "Not Updated", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        });
            }
        });
    }
}