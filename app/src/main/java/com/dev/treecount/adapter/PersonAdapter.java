package com.dev.treecount.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import com.dev.treecount.DetalleMiembroActivity;
import com.google.gson.Gson;
import com.dev.treecount.R;
import com.dev.treecount.model.Person;

/**
 * Created by Marlon on 15/08/2018.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder>  {
    private List<Person> items;
    private Gson gson = new Gson();
    private Context context;

    public PersonAdapter(Context context, List<Person> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.miembro_card, viewGroup, false);
        return new PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.PersonViewHolder viewHolder, final int i) {

        final Person person = items.get(i);

        viewHolder.lblNombre.setText(person.getNombre()+" "+person.getApellido());
        viewHolder.lblDNI.setText("DNI: " + person.getDni());
        viewHolder.lblCargo.setText("Cargo: " + String.valueOf(person.getCargo()));
        viewHolder.lblBrigada.setText("N° de Brigada: " + String.valueOf(person.getIdBrigada()));

        //Implementando evento CLIC en el CardView
        /*
        viewHolder.personCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Bundle bundle = new Bundle();
                //bundle.putInt("vId", items.get(i).getId());
                //bundle.putInt("vFoto", items.get(i).getIdphoto());
                //bundle.putString("vNombre", items.get(i).getNombre());
                //bundle.putString("vDNI", items.get(i).getDni());
                //bundle.putString("vBiografia", items.get(i).getBiografia());

                Intent newActivity = new Intent(v.getContext(), DetalleMiembroActivity.class);
                newActivity.putExtra("person", gson.toJson(person));
                //newActivity.putExtras(bundle);
                v.getContext().startActivity(newActivity);
            }
        });
        */

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public CardView personCardView;
        public TextView lblDNI;
        public TextView lblNombre;
        public TextView lblCargo;
        public TextView lblBrigada;

        public PersonViewHolder(@NonNull View v) {
            super(v);
            personCardView = (CardView) v.findViewById(R.id.personCardView);
            lblCargo = (TextView) v.findViewById(R.id.lblCargo);
            lblNombre = (TextView) v.findViewById(R.id.lblNombre);
            lblDNI = (TextView) v.findViewById(R.id.lblDNI);
            lblBrigada = (TextView) v.findViewById(R.id.lblBrigada);
        }
    }

    /*
    public PersonAdapter(List<Person> items) {
        this.items = items;
    }

    public List<Person> getItems(){
        return this.items;
    }*/


}
