package com.example.yan.apptrabalho1.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yan.apptrabalho1.Modelo.Evento;
import com.example.yan.apptrabalho1.R;

import java.util.ArrayList;

public class ListaEventoParaParticipanteAdapter  extends RecyclerView.Adapter<ListaEventoParaParticipanteAdapter.ViewHolder>  {
    private ArrayList<Evento> eventos = new ArrayList<>();
    private OnEventoParaParticipanteClickListener listener;

    public void setEventos(ArrayList<Evento> e) {
        eventos = e;
        notifyDataSetChanged();
    }

    public interface OnEventoParaParticipanteClickListener {
        void onEventoParaParticipanteClick(View view, int position);
        void onLongEventoParaParticipanteClick(View view, int position);
    }

    public void setOnEventoParaParticipanteClickListener(OnEventoParaParticipanteClickListener listener){
        this.listener = listener;
    }

    public ListaEventoParaParticipanteAdapter(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }

    @NonNull
    @Override
    public ListaEventoParaParticipanteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View lstEventosParaParticipantesView = inflater.inflate(R.layout.rv_listar_eventos_para_participante,viewGroup,false);
        ViewHolder holder = new ListaEventoParaParticipanteAdapter.ViewHolder(lstEventosParaParticipantesView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ListaEventoParaParticipanteAdapter.ViewHolder holder, int position) {
        holder.txtTituloEvento.setText(eventos.get(position).getTitulo());
        holder.txtDataEvento.setText(eventos.get(position).getDia());
        holder.txtHoraEvento.setText(eventos.get(position).getHora());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public TextView txtTituloEvento;
        public TextView txtDataEvento;
        public TextView txtHoraEvento;

        public ViewHolder(View itemView) {
            super(itemView);

            txtTituloEvento = itemView.findViewById(R.id.rv_lst_eventos_para_participante_Titulo);
            txtDataEvento = itemView.findViewById(R.id.rv_lst_eventos_para_participante_Data);
            txtHoraEvento = itemView.findViewById(R.id.rv_lst_eventos_para_participante_Hora);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onLongEventoParaParticipanteClick(view, position);
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
                            listener.onEventoParaParticipanteClick(v, position);
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
                    listener.onEventoParaParticipanteClick(v, position);
                }
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (listener!=null){
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listener.onLongEventoParaParticipanteClick(view, position);
                }
            }
            return true;
        }
    }
}
