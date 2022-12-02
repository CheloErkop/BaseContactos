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
import com.google.firebase.firestore.DocumentSnapshot;
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

        //Busca el id que esta en el ViewHolder
        String id = getIntent().getStringExtra("id_contacto");

        nombre = findViewById(R.id.txtNombre);
        telefono = findViewById(R.id.txtTelefono);
        correo = findViewById(R.id.txtCorreo);
        btn_add= findViewById(R.id.btnRegistrar);


        //Si el id esta vacio, este se va a crear al momento de agregar un contacto
        if (id == null || id == ""){
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = nombre.getText().toString();
                    String number = telefono.getText().toString();
                    String email = correo.getText().toString();

                    if (name.isEmpty()&& number.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                    }else{
                        postContacto (name,number,email);
                    }
                }
            });
        }else{
            //si querremos modificar un contacto, este abrira una ventana pidiendo los datos y modificando mediante el id
            btn_add.setText("Modificar");
            getContacto(id);
            btn_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = nombre.getText().toString();
                    String number = telefono.getText().toString();
                    String email = correo.getText().toString();

                    if (name.isEmpty()&& number.isEmpty()){
                        Toast.makeText(getApplicationContext(), "Ingresar los datos", Toast.LENGTH_SHORT).show();
                    }else{
                        updateContacto (name,number,email,id);
                    }
                }
            });
                }
            }

    private void updateContacto(String name, String number, String email, String id) {
        //Modifica los contactos mediante el id que creamos (getContacto), lo manda a una actividad nueva para modificarlos
        Map<String, Object> map = new HashMap<>();
        map.put("nombre", nombre.getText().toString());
        map.put("telefono", telefono.getText().toString());
        map.put("correo",   correo.getText().toString());

        mFirestore.collection("contactos").document(id).update(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(getApplicationContext(), "Modificado Exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al Modificar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void postContacto(String name, String number, String email) {
        //Crea el Contacto a traves de los Plain Text y los manda al Firebase
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

    private void getContacto(String id){
        //Dejar los contactos en un id
        mFirestore.collection("pet").document(id).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String nameContacto = documentSnapshot.getString("nombre");
                String phoneContacto = documentSnapshot.getString("telefono");
                String mailContacto = documentSnapshot.getString("correo");

                nombre.setText(nameContacto);
                telefono.setText(phoneContacto);
                correo.setText(mailContacto);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Error al Modificar", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return false;
    }
}