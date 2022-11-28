package com.example.basecontactos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainCrearContacto extends AppCompatActivity {

    Button btn_add;
    EditText nombre, telefono, correo;
    private FirebaseFirestore mFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_crear_contacto);

        this.setTitle("Agregar Contacto");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mFirestore = FirebaseFirestore.getInstance();

        nombre = findViewById(R.id.txtNombre);
        telefono = findViewById(R.id.txtTelefono);
        correo = findViewById(R.id.txtCorreo);
        btn_add= findViewById(R.id.btnRegistrar);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nombre.getText().toString();
                String number = telefono.getText().toString();
                String email = correo.getText().toString();

                if (name.isEmpty()&& number.isEmpty()&& email.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                }else{
                    postContacto (name,number,email);
                }
            }
        });

    }

    private void postContacto(String name, String number, String email) {
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombre.getText().toString());
        map.put("telefono", telefono.getText().toString());
        map.put("correo",   correo.getText().toString());


        mFirestore.collection("contactos").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getApplicationContext(), "Creado Exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al Ingresar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}