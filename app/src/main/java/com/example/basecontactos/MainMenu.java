package com.example.basecontactos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    Button  botonCalculadora;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        botonCalculadora = findViewById(R.id.btnCalculadora);
        botonCalculadora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i1= new Intent(MainMenu.this, MainCalculadora.class);
                startActivity(i1);
            }
        });
    }

    public void Agregar(View view) {
        Intent a = new Intent(MainMenu.this, MainCrearContacto.class);
        startActivity(a);
    }

    public void VerContactos(View view) {
        Intent b = new Intent (MainMenu.this, MainContacto.class);
        startActivity(b);
    }

}