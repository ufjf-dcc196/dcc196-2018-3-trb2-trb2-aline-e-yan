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
import com.example.yan.apptrabalho1.Persistence.ParticipanteEventoDao;
import com.example.yan.apptrabalho1.R;

import java.util.ArrayList;

public class ListarEventosParaParticipanteActivity extends AppCompatActivity {
    private ArrayList<Evento> eventos = new ArrayList<>();
    private RecyclerView rvEventosParaParticipante;
    private int idParticipante;
    private ListaEventoParaParticipanteAdapter adapter;
    public static final String ORIGEM_PARTICIPANTE = "Origem de onde foi chamada a activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_eventos_para_participante);
        EventoDao.getInstance().inicializarDBHelper(getApplicationContext());
        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        idParticipante = bundleResult.getInt(AtualizarPessoaActivity.ID_PARTICIPANTE);

        rvEventosParaParticipante = findViewById(R.id.rv_listar_eventos_para_participante);
        rvEventosParaParticipante.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListaEventoParaParticipanteAdapter(
                ParticipanteEventoDao.getInstance().
                        getEventosNaoInscritos(idParticipante)
        );
        rvEventosParaParticipante.setAdapter(adapter);
        adapter.setOnEventoParaParticipanteClickListener(new ListaEventoParaParticipanteAdapter.OnEventoParaParticipanteClickListener() {
            @Override
            public void onEventoParaParticipanteClick(View view, int position) {
                Intent attPart = new Intent();

                int idEvento =
                        //EventoDao.getInstance().getEventos().get(position).getId();
                        ParticipanteEventoDao.getInstance().
                        getEventosNaoInscritos(idParticipante).get(position).getId();
                        ParticipanteEventoDao.getInstance().addPartipanteEvento(idEvento, idParticipante);

                setResult(Activity.RESULT_OK, attPart);
                finish();
            }

            @Override
            public void onLongEventoParaParticipanteClick(View view, int position) {
                Intent attPart = new Intent(ListarEventosParaParticipanteActivity.this, DetalhesEventoActivity.class);
                int idEvento = EventoDao.getInstance().getEventos().get(position).getId();
                attPart.putExtra(ListarEventosActivity.ID_EVENTO, idEvento);
                attPart.putExtra(ListarEventosParaParticipanteActivity.ORIGEM_PARTICIPANTE, true);
                startActivity(attPart);
            }
        });

    }

}
