package com.example.yan.apptrabalho1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yan.apptrabalho1.Adapter.ListaEventoAdapter;
import com.example.yan.apptrabalho1.Adapter.ListaParticpanteAdapter;
import com.example.yan.apptrabalho1.Modelo.Evento;
import com.example.yan.apptrabalho1.R;

import java.util.ArrayList;

public class ListarEventosActivity extends AppCompatActivity {

    private RecyclerView rcListaEvento;
    private ListaEventoAdapter listaEventosAdapter;
    private ArrayList<Evento> eventos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_eventos);



        rcListaEvento=(RecyclerView)findViewById(R.id.rclistaevento);

        rcListaEvento.setLayoutManager(new LinearLayoutManager(this));

        listaEventosAdapter = new ListaEventoAdapter(eventos , false);

        rcListaEvento.setAdapter(listaEventosAdapter);




    }
}
