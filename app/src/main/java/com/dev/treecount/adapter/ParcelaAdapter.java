package com.dev.treecount.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev.treecount.R;
import com.dev.treecount.model.Parcela;

import java.util.List;

public class ParcelaAdapter extends RecyclerView.Adapter<ParcelaAdapter.ParcelaViewHolder> {
    private List<Parcela> items;

    public ParcelaAdapter(List<Parcela> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ParcelaAdapter.ParcelaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.parcela_card, viewGroup, false);
        return new ParcelaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ParcelaAdapter.ParcelaViewHolder parcelaViewHolder, int i) {
        parcelaViewHolder.lblNombre.setText(items.get(i).getNombre());
        parcelaViewHolder.lblBrigadaNombre.setText(items.get(i).getBrigadaNombre());
        parcelaViewHolder.lblLatitud.setText(String.valueOf(items.get(i).getRefLatitud()));
        parcelaViewHolder.lblLongitud.setText(String.valueOf(items.get(i).getRefLongitud()));
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

        public ParcelaViewHolder(@NonNull View itemView) {
            super(itemView);
            parcelaCardView = (CardView) itemView.findViewById(R.id.parcelaCardView);
            lblNombre = (TextView) itemView.findViewById(R.id.lblNombre);
            lblBrigadaNombre = (TextView) itemView.findViewById(R.id.lblBrigadaNombre);
            lblLatitud = (TextView) itemView.findViewById(R.id.lblLatitud);
            lblLongitud = (TextView) itemView.findViewById(R.id.lblLongitud);
        }
    }
}