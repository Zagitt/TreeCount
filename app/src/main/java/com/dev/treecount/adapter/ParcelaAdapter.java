package com.dev.treecount.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dev.treecount.DatosParcelaActivity;
import com.dev.treecount.MainMenuActivity;
import com.dev.treecount.R;
import com.dev.treecount.model.Parcela;
import com.google.gson.Gson;

import java.util.List;

public class ParcelaAdapter extends RecyclerView.Adapter<ParcelaAdapter.ParcelaViewHolder> {
    private List<Parcela> items;
    private Context context;

    public ParcelaAdapter(Context context, List<Parcela> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public ParcelaAdapter.ParcelaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.parcela_card, viewGroup, false);
        return new ParcelaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ParcelaAdapter.ParcelaViewHolder parcelaViewHolder, int i) {

        final Parcela parcela = items.get(i);

        parcelaViewHolder.lblNombre.setText(parcela.getNombre());
        parcelaViewHolder.lblBrigadaNombre.setText("Brigada: " + parcela.getBrigadaNombre());
        parcelaViewHolder.lblLatitud.setText(String.valueOf("Latitud: " + parcela.getRefLatitud()));
        parcelaViewHolder.lblLongitud.setText(String.valueOf("Longitud: " + parcela.getRefLongitud()));

        parcelaViewHolder.btnInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // AQUI LOS DETALLES

            }
        });

        parcelaViewHolder.btnDetalles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, DatosParcelaActivity.class);
                intent.putExtra("parcela", new Gson().toJson(parcela));
                context.startActivity(intent);

            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ParcelaViewHolder extends RecyclerView.ViewHolder {
        public CardView parcelaCardView;
        public TextView lblNombre;
        public TextView lblBrigadaNombre;
        public TextView lblLatitud;
        public TextView lblLongitud;
        public Button btnInventario;
        public Button btnDetalles;

        public ParcelaViewHolder(@NonNull View itemView) {
            super(itemView);
            parcelaCardView = (CardView) itemView.findViewById(R.id.parcelaCardView);
            lblNombre = (TextView) itemView.findViewById(R.id.lblNombre);
            lblBrigadaNombre = (TextView) itemView.findViewById(R.id.lblBrigadaNombre);
            lblLatitud = (TextView) itemView.findViewById(R.id.lblLatitud);
            lblLongitud = (TextView) itemView.findViewById(R.id.lblLongitud);
            btnInventario  = (Button) itemView.findViewById(R.id.btnInventario);
            btnDetalles  = (Button) itemView.findViewById(R.id.btnDetalles);
        }
    }
}