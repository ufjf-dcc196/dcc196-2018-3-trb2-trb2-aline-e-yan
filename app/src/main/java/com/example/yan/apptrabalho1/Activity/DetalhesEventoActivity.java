package com.example.yan.apptrabalho1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.yan.apptrabalho1.Adapter.ListaParticpanteAdapter;
import com.example.yan.apptrabalho1.Persistence.EventoDao;
import com.example.yan.apptrabalho1.Persistence.ParticipanteDao;
import com.example.yan.apptrabalho1.R;

public class DetalhesEventoActivity extends AppCompatActivity {
    private RecyclerView rvParticipantesEvento;
    private ListaParticpanteAdapter adapter;
    private Button btnEditarInfoEvento;
    private int posicaoEvento;
    private int posicaoParticipante;

    private boolean testeOringemChamado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);
        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        rvParticipantesEvento = findViewById(R.id.rcDetalhesEvento);
        posicaoEvento = bundleResult.getInt(ListarEventosActivity.POSICAO_EVENTO);
        adapter = new ListaParticpanteAdapter(EventoDao.getInstance().getEventos().get(posicaoEvento).getParticipantes());
        rvParticipantesEvento.setLayoutManager(new LinearLayoutManager(this));
        rvParticipantesEvento.setAdapter(adapter);
        adapter.setOnParticipanteClickListener(new ListaParticpanteAdapter.OnParticipanteClickListener() {
            @Override
            public void onParticipanteClick(View view, int position) {

            }

            @Override
            public void onLongParticipanteClick(View view, int position) {
                ParticipanteDao.getInstance().removeParticipanteDoEvento(
                        EventoDao.getInstance().getEventos().get(posicaoEvento),
                        EventoDao.getInstance().getEventos().get(posicaoEvento).getParticipantes().get(position));

                EventoDao.getInstance().getEventos().get(posicaoEvento).getParticipantes().remove(position);
                adapter.notifyDataSetChanged();
            }
        });
    }
}
