package com.example.basecontactos.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basecontactos.MainCrearContacto;
import com.example.basecontactos.R;
import com.example.basecontactos.model.Contacto;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ContactoAdapter extends FirestoreRecyclerAdapter<Contacto, ContactoAdapter.ViewHolder> {
    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    Activity activity;
    FragmentManager fm;

    public ContactoAdapter(@NonNull FirestoreRecyclerOptions<Contacto> options, Activity activity, FragmentManager fm ) {
        super(options);
        this.activity = activity;
        this.fm = fm;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder viewHolder, int i, @NonNull Contacto Contacto) {
        DocumentSnapshot documentSnapshot = getSnapshots().getSnapshot(viewHolder.getAdapterPosition());
        final String id = documentSnapshot.getId();

        viewHolder.nombre.setText(Contacto.getNombre());
        viewHolder.telefono.setText(Contacto.getTelefono());
        viewHolder.correo.setText(Contacto.getCorreo());
        viewHolder.editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, MainCrearContacto.class);
                i.putExtra("id_contacto", id);
                activity.startActivity(i);
            }
        });

        viewHolder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteContacto(id);
            }
        });

    }

    private void deleteContacto(String id) {
        mFirestore.collection("contactos").document(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(activity, "Eliminado Correctamente", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(activity, "Error al eliminar", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_contact_list,parent, false);
        return new ViewHolder(v);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, telefono, correo;
        ImageView eliminar,editar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre =itemView.findViewById(R.id.nombre);
            telefono =itemView.findViewById(R.id.telefono);
            correo =itemView.findViewById(R.id.correo);
            eliminar = itemView.findViewById(R.id.btnEliminar);
            editar = itemView.findViewById(R.id.btnEditar);
        }
    }
}
