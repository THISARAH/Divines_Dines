package com.example.loginversion_03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

public class adminActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    MainAdapterAdmin mainAdapterAdmin;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        recyclerView = (RecyclerView)findViewById(R.id.rvAdmin);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModelAdmin> options =
                new FirebaseRecyclerOptions.Builder<MainModelAdmin>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Food_Details"), MainModelAdmin.class)
                        .build();


        mainAdapterAdmin = new MainAdapterAdmin(options);
        recyclerView.setAdapter(mainAdapterAdmin);

        floatingActionButton = (FloatingActionButton)findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddActivityF.class));

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        mainAdapterAdmin.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapterAdmin.stopListening();
    }
}