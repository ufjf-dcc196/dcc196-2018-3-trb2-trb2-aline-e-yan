package com.example.yan.apptrabalho1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yan.apptrabalho1.Adapter.ListaEventoAdapter;
import com.example.yan.apptrabalho1.Persistence.EventoDao;
import com.example.yan.apptrabalho1.Persistence.ParticipanteDao;
import com.example.yan.apptrabalho1.R;

public class ListarEventosActivity extends AppCompatActivity {

    private RecyclerView rcListaEvento;
    private static ListaEventoAdapter listaEventosAdapter;
    public static final String POSICAO_EVENTO = "Posição Evento";

    public static void attRecycle() {
        listaEventosAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_eventos);
        EventoDao.getInstance().inicializarDBHelper(getApplicationContext());

        rcListaEvento=(RecyclerView)findViewById(R.id.rclistaevento);

        rcListaEvento.setLayoutManager(new LinearLayoutManager(this));

        listaEventosAdapter = new ListaEventoAdapter(EventoDao.getInstance().getEventos());

        listaEventosAdapter.setEventos(EventoDao.getInstance().getEventos());
        rcListaEvento.setAdapter(listaEventosAdapter);

        listaEventosAdapter.setOnEventoClickListener(new ListaEventoAdapter.OnEventoClickListener() {
            @Override
            public void onEventoClick(View view, int position) {
                Intent intent = new Intent(ListarEventosActivity.this, DetalhesEventoActivity.class);
                intent.putExtra(ListarEventosActivity.POSICAO_EVENTO, position);
                intent.putExtra(ListarEventosParaParticipanteActivity.ORIGEM_PARTICIPANTE, false);
                startActivity(intent);

            }

            @Override
            public void onLongEventoClick(View view, int position) {

       //         ParticipanteDao.getInstance().removeAllParticipanteEvento(EventoDao.getInstance().getEventos().get(position));
                EventoDao.getInstance().getEventos().remove(position);
                listaEventosAdapter.notifyDataSetChanged();
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        listaEventosAdapter.setEventos(EventoDao.getInstance().getEventos());
    }
}
