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

public class ListaEventoAdapter extends RecyclerView.Adapter<ListaEventoAdapter.ViewHolder>{


    private ArrayList<Evento> eventos = new ArrayList<>();
    private OnEventoClickListener listener;

    public void setEventos(ArrayList<Evento> e) {
        eventos = e;
        notifyDataSetChanged();
    }

    public interface OnEventoClickListener {
        void onEventoClick(View view, int position);
        void onLongEventoClick(View view, int position);
    }

    public void setOnEventoClickListener(OnEventoClickListener listener){
        this.listener = listener;
    }

    public ListaEventoAdapter(ArrayList<Evento> eventos) {
        this.eventos = eventos;
    }


    @NonNull
    @Override
    public ListaEventoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View lstEventoView;
        lstEventoView = inflater.inflate(R.layout.rv_lista_eventos, viewGroup, false);
        ViewHolder holderView = new ViewHolder(lstEventoView);
        return holderView;
    }


    @Override
    public void onBindViewHolder(@NonNull ListaEventoAdapter.ViewHolder holder, int position) {
        holder.txtTituloEvento.setText(eventos.get(position).getTitulo());
        holder.txtNumEvento.setText(" "+String.valueOf(position+1));
        holder.txtDataEvento.setText(eventos.get(position).getDia());
        holder.txtHoraEvento.setText(eventos.get(position).getHora());
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView txtTituloEvento;
        public TextView txtNumEvento;
        public TextView txtDataEvento;
        public TextView txtHoraEvento;
        public ViewHolder(View itemView) {
            super(itemView);

            txtTituloEvento = itemView.findViewById(R.id.rv_lst_eventos_Titulo);
            txtNumEvento= itemView.findViewById(R.id.textVierv_lst_eventos_num);
            txtDataEvento = itemView.findViewById(R.id.rv_lst_eventos_Data);
            txtHoraEvento = itemView.findViewById(R.id.rv_lst_eventos_Hora);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onLongEventoClick(view, position);
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
                            listener.onEventoClick(v, position);
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
                    listener.onEventoClick(v, position);
                }
            }
        }

        @Override
        public boolean onLongClick(View view) {
            if (listener!=null){
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION){
                    listener.onLongEventoClick(view, position);
                }
            }
            return true;
        }

    }

}
