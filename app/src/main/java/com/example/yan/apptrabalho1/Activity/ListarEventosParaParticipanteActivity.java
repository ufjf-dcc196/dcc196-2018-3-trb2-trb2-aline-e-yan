package com.example.yan.apptrabalho1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.yan.apptrabalho1.Adapter.ListaEventoParaParticipanteAdapter;
import com.example.yan.apptrabalho1.Modelo.Evento;
import com.example.yan.apptrabalho1.Persistence.EventoDao;
import com.example.yan.apptrabalho1.Persistence.ParticipanteDao;
import com.example.yan.apptrabalho1.R;

import java.util.ArrayList;

public class ListarEventosParaParticipanteActivity extends AppCompatActivity {
    private ArrayList<Evento> eventos = new ArrayList<>();
    private RecyclerView rvEventosParaParticipante;
    private int posicaoParticipante;
    private ListaEventoParaParticipanteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_eventos_para_participante);
        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        posicaoParticipante = bundleResult.getInt(AtualizarPessoaActivity.POSICAO_PARTICIPANTE);
        instanciaEventos(posicaoParticipante);
        rvEventosParaParticipante = findViewById(R.id.rv_listar_eventos_para_participante);
        rvEventosParaParticipante.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListaEventoParaParticipanteAdapter(eventos);
        rvEventosParaParticipante.setAdapter(adapter);
        adapter.setOnEventoParaParticipanteClickListener(new ListaEventoParaParticipanteAdapter.OnEventoParaParticipanteClickListener() {
            @Override
            public void onEventoParaParticipanteClick(View view, int position) {
                Intent attPart = new Intent();
                ParticipanteDao.getInstance().getParticipantes().get(posicaoParticipante).addEvento(eventos.get(position));
                setResult(Activity.RESULT_OK, attPart);
                finish();
            }

            @Override
            public void onLongEventoParaParticipanteClick(View view, int position) {

            }
        });

    }

    private void instanciaEventos(int posicaoParticipante) {
        for (Evento e: EventoDao.getInstance().getEventos()) {
            if(!ParticipanteDao.getInstance().getParticipantes().get(posicaoParticipante).getMeusEventos().contains(e)){
                eventos.add(e);
            }
        }
    }
}
