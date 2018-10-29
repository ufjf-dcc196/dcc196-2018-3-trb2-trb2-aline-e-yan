package com.example.yan.apptrabalho1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yan.apptrabalho1.Activity.ListarEventosActivity;
import com.example.yan.apptrabalho1.Modelo.Evento;
import com.example.yan.apptrabalho1.Modelo.Participante;
import com.example.yan.apptrabalho1.R;

import java.util.ArrayList;

public class ListaEventoAdapter extends RecyclerView.Adapter<ListaEventoAdapter.ViewHolder>{


    private ArrayList<Evento> eventos = new ArrayList<>();

    public ListaEventoAdapter(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }


    @NonNull
    @Override
    public ListaEventoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View lstParticipantesView = inflater.inflate(R.layout.listparticipante,viewGroup,false);
        ListaEventoAdapter.ViewHolder holder = new ListaEventoAdapter.ViewHolder(lstParticipantesView);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ListaEventoAdapter.ViewHolder holder, int position) {
        holder.txtNomeEvento.setText(eventos.get(position).getTitulo());
        holder.txtNumEvento.setText(String.valueOf(position));
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtNomeEvento;
        public TextView txtNumEvento;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNomeEvento= itemView.findViewById(R.id.lst_event_listaeventos);
            txtNumEvento= itemView.findViewById(R.id.lst_event_numEvento);
        }
}

}
