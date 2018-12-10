package com.dev.treecount.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.treecount.R;
import com.dev.treecount.model.Inventario;
import com.google.gson.Gson;

import java.util.List;

public class InventarioAdapter extends RecyclerView.Adapter<InventarioAdapter.InventarioViewHolder>  {
    private List<Inventario> items;
    private Gson gson = new Gson();
    private Context context;

    public InventarioAdapter(Context context, List<Inventario> items) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public InventarioViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.inventario_card, viewGroup, false);
        return new InventarioViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InventarioAdapter.InventarioViewHolder viewHolder, final int i) {

        final Inventario inventario = items.get(i);

        viewHolder.lblNomComun.setText("Nombre Común: " + inventario.getNom_comun());
        viewHolder.lblNomCientifico.setText("Nombre Científico: " + inventario.getNom_cientifico());
        viewHolder.lblCoordenadas.setText("Coordenadas: \nLatitud: " + inventario.getLat()+"\nLongitud: " + items.get(i).getLon());
        viewHolder.lblDap.setText("DAP: " + inventario.getDap());
        viewHolder.lblAltFuste.setText("Altura Fuste: " + inventario.getAltura_fuste());
        viewHolder.lblAltTotal.setText("Altura Total: " + inventario.getAltura_total());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class InventarioViewHolder extends RecyclerView.ViewHolder {
        // Campos respectivos de un item
        public CardView inventarioCardView;
        public TextView lblNomComun;
        public TextView lblNomCientifico;
        public TextView lblCoordenadas;
        public TextView lblDap;
        public TextView lblAltFuste;
        public TextView lblAltTotal;

        public InventarioViewHolder(@NonNull View v) {
            super(v);
            inventarioCardView = (CardView) v.findViewById(R.id.inventarioCardView);
            lblNomComun = (TextView) v.findViewById(R.id.lblNomComun);
            lblNomCientifico = (TextView) v.findViewById(R.id.lblNomCientifico);
            lblCoordenadas = (TextView) v.findViewById(R.id.lblCoordenadas);
            lblDap = (TextView) v.findViewById(R.id.lblDap);
            lblAltFuste = (TextView) v.findViewById(R.id.lblAltFuste);
            lblAltTotal = (TextView) v.findViewById(R.id.lblAltTotal);
        }
    }
}
