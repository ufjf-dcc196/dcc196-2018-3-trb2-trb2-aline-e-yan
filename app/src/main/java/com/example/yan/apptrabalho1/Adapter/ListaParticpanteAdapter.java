package com.example.yan.apptrabalho1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yan.apptrabalho1.Modelo.Participante;
import com.example.yan.apptrabalho1.R;

import java.util.ArrayList;

public class ListaParticpanteAdapter extends RecyclerView.Adapter<ListaParticpanteAdapter.ViewHolder> {
    private ArrayList<Participante> participantes = new ArrayList<>();

    public ListaParticpanteAdapter(ArrayList<Participante> participantes) {
        this.participantes = participantes;
    }


    @NonNull
    @Override
    public ListaParticpanteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View lstParticipantesView = inflater.inflate(R.layout.listparticipante,viewGroup,false);
        ViewHolder holder = new ViewHolder(lstParticipantesView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaParticpanteAdapter.ViewHolder holder, int position) {
        holder.txtNomeParticpante.setText(participantes.get(position).getNome());
        holder.txtNumParticpante.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
         return participantes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtNomeParticpante;
        public TextView txtNumParticpante;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNomeParticpante= itemView.findViewById(R.id.lst_part_listaparticpante);
            txtNumParticpante = itemView.findViewById(R.id.lst_part_numParticipante);
        }
    }

}
