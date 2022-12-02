package com.example.basecontactos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.basecontactos.adapter.ContactoAdapter;
import com.example.basecontactos.model.Contacto;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainContacto extends AppCompatActivity {

    RecyclerView mRecycler;
    ContactoAdapter cAdapter;
    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_contacto);
        mFirestore = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recyclerView);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = mFirestore.collection("contactos");
        FirestoreRecyclerOptions<Contacto> firestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Contacto>().setQuery(query, Contacto.class).build();

        cAdapter = new ContactoAdapter(firestoreRecyclerOptions, this, getSupportFragmentManager());
        cAdapter.notifyDataSetChanged();
        mRecycler.setAdapter(cAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cAdapter.stopListening();
    }
}