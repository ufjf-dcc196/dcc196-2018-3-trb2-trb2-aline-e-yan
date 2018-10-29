package com.example.yan.apptrabalho1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yan.apptrabalho1.Adapter.ListaParticpanteAdapter;
import com.example.yan.apptrabalho1.Persistence.ParticipanteDao;
import com.example.yan.apptrabalho1.R;

public class InscritosActivity extends AppCompatActivity {
    private RecyclerView rvParticipantesEvento;
    private ListaParticpanteAdapter adapter;
    private int posicaoEvento;
    private int posicaoParticipante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_participantes);
        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        rvParticipantesEvento = (RecyclerView) findViewById(R.id.rclistaparticipantes);
        posicaoEvento = bundleResult.getInt(AtualizarPessoaActivity.POSICAO_EVENTO);
        posicaoParticipante = bundleResult.getInt(AtualizarPessoaActivity.POSICAO_PARTICIPANTE);
        adapter = new ListaParticpanteAdapter(ParticipanteDao.getInstance().getParticipantes()
                .get(posicaoParticipante).getMeusEventos().get(posicaoEvento).getParticipantes());

        rvParticipantesEvento.setLayoutManager(new LinearLayoutManager(this));
        rvParticipantesEvento.setAdapter(adapter);
    }
}
