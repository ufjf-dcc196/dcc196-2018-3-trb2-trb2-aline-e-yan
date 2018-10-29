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
    private OnParticipanteClickListener listener;


    public interface OnParticipanteClickListener {
        void onParticipanteClick(View view, int position);
        void onLongParticipanteClick(View view, int position);
    }

    public void setOnParticipanteClickListener(OnParticipanteClickListener listener){
        this.listener = listener;
    }

    public ListaParticpanteAdapter(ArrayList<Participante> participantes) {
        this.participantes = participantes;

    }


    @NonNull
    @Override
    public ListaParticpanteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View lstParticipantesView = inflater.inflate(R.layout.rv_lista_participante, viewGroup, false);
        ViewHolder holder = new ViewHolder(lstParticipantesView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaParticpanteAdapter.ViewHolder holder, int position) {
        holder.txtNomeParticpante.setText(participantes.get(position).getNome());
        holder.txtNumParticpante.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
         return participantes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView txtNomeParticpante;
        public TextView txtNumParticpante;

        public ViewHolder(View itemView) {
            super(itemView);

            txtNomeParticpante= itemView.findViewById(R.id.lst_part_listaparticpante);
            txtNumParticpante = itemView.findViewById(R.id.lst_part_numParticipante);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onLongParticipanteClick(view, position);
                        }
                    }
                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v){
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onParticipanteClick(v, position);
                        }
                    }
                }
            });


        }

        @Override
        public void onClick(View v){
            if (listener!=null){
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listener.onParticipanteClick(v, position);
                }
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (listener!=null){
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listener.onLongParticipanteClick(view, position);
                }
            }
            return true;
        }
    }
}


