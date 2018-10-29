package com.example.yan.apptrabalho1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yan.apptrabalho1.Adapter.ListaEventoAdapter;
import com.example.yan.apptrabalho1.Adapter.ListaParticpanteAdapter;
import com.example.yan.apptrabalho1.Modelo.Evento;
import com.example.yan.apptrabalho1.Persistence.EventoDao;
import com.example.yan.apptrabalho1.Persistence.ParticipanteDao;
import com.example.yan.apptrabalho1.R;

import java.util.ArrayList;

public class ListarEventosActivity extends AppCompatActivity {

    private RecyclerView rcListaEvento;
    private ListaEventoAdapter listaEventosAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_eventos);



        rcListaEvento=(RecyclerView)findViewById(R.id.rclistaevento);

        rcListaEvento.setLayoutManager(new LinearLayoutManager(this));

        listaEventosAdapter = new ListaEventoAdapter(EventoDao.getInstance().getEventos());

        rcListaEvento.setAdapter(listaEventosAdapter);

        listaEventosAdapter.setOnEventoClickListener(new ListaEventoAdapter.OnEventoClickListener() {
            @Override
            public void onEventoClick(View view, int position) {

            }

            @Override
            public void onLongEventoClick(View view, int position) {
                ParticipanteDao.getInstance().removeParticipanteEvento(EventoDao.getInstance().getEventos().get(position));
                EventoDao.getInstance().getEventos().remove(position);
                listaEventosAdapter.notifyDataSetChanged();
            }
        });




    }
}
