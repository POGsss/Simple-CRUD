package com.example.simplecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    // Declaration
    FloatingActionButton addFab;
    RecyclerView outputRv;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization
        addFab = findViewById(R.id.fabAdd);
        outputRv = findViewById(R.id.outputRv);

        // Floating Action Bar
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        // Recycler View
        outputRv.setLayoutManager(new LinearLayoutManager(this));

        // Firebase
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("data"), MainModel.class)
                        .build();

        mainAdapter = new MainAdapter(options);
        outputRv.setAdapter(mainAdapter);
    }

    // Start Main Adapter
    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }
}