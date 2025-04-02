package com.example.simplecrud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    // Declaration
    FloatingActionButton addFab;
    RecyclerView outputRv;
    MainAdapter mainAdapter;
    SearchView searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialization
        addFab = findViewById(R.id.fabAdd);
        outputRv = findViewById(R.id.outputRv);
        searchBar = findViewById(R.id.searchBar);

        // Floating Action Bar
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        // Recycler View
        outputRv.setLayoutManager(new GridLayoutManager(this, 1));

        // Firebase
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("data").orderByChild("title"), MainModel.class)
                        .build();

        mainAdapter = new MainAdapter(options);
        outputRv.setAdapter(mainAdapter);

        // Search Bar
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchText(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                searchText(query);
                return false;
            }
        });
    }

    // Search Bar Function
    public void searchText(String query) {
        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("data").orderByChild("title").startAt(query).endAt(query+"~"), MainModel.class)
                        .build();

        mainAdapter = new MainAdapter(options);
        outputRv.setAdapter(mainAdapter);
        mainAdapter.startListening();
    }

    // Start Main Adapter
    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }
}