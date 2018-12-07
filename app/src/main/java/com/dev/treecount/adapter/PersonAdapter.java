package com.dev.treecount.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.dev.treecount.BrigadaActivity;
import com.dev.treecount.R;
import com.dev.treecount.model.Person;

/**
 * Created by Marlon on 15/08/2018.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder>  {
    private List<Person> items;

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public CardView personCardView;
        public ImageView imgFoto;
        public TextView lblNombre;
        public TextView lblDNI;
        public TextView lblEdad;

        public PersonViewHolder(View v) {
            super(v);
            personCardView = (CardView) v.findViewById(R.id.personCardView);
            imgFoto = (ImageView) v.findViewById(R.id.imgFoto);
            lblNombre = (TextView) v.findViewById(R.id.lblNombre);
            lblDNI = (TextView) v.findViewById(R.id.lblDNI);
            lblEdad = (TextView) v.findViewById(R.id.lblEdad);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public PersonAdapter(List<Person> items) {
        this.items = items;
    }

    public List<Person> getItems(){
        return this.items;
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.miembro_brigada, viewGroup, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonViewHolder viewHolder, final int i) {
        viewHolder.imgFoto.setImageResource(items.get(i).getIdphoto());
        viewHolder.lblNombre.setText(items.get(i).getNombre());
        viewHolder.lblDNI.setText("DNI: " + items.get(i).getDni());
        viewHolder.lblEdad.setText("Edad: " + String.valueOf(items.get(i).getEdad()));

        //Implementando evento CLIC en el CardView
        viewHolder.personCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("vId", items.get(i).getId());
                bundle.putInt("vFoto", items.get(i).getIdphoto());
                bundle.putString("vNombre", items.get(i).getNombre());
                bundle.putString("vDNI", items.get(i).getDni());
                bundle.putString("vBiografia", items.get(i).getBiografia());

                Intent newActivity = new Intent(v.getContext(), BrigadaActivity.class);
                newActivity.putExtras(bundle);
                v.getContext().startActivity(newActivity);
            }
        });
    }
}
