package com.example.yan.apptrabalho1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yan.apptrabalho1.Adapter.ListaParticpanteAdapter;
import com.example.yan.apptrabalho1.Modelo.Participante;
import com.example.yan.apptrabalho1.Persistence.EventoDao;
import com.example.yan.apptrabalho1.Persistence.ParticipanteDao;
import com.example.yan.apptrabalho1.R;

public class DetalhesEventoActivity extends AppCompatActivity {
    public static final String POSICAO_PARTICIPANTE = "Posição Participante";
    public static final String POSICAO_EVENTO = "Posiçao do Evento";
    private static final int REQUEST_ATUALIZAR_EVENTO=1;

    private RecyclerView rvParticipantesEvento;
    private ListaParticpanteAdapter adapter;
    private Button btnEditarInfoEvento;
    private TextView txtTitulo,txtData,txtHorario,txtFacilitador,txtDescricao;
    private int posicaoEvento;
    private int posicaoParticipante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_evento);
        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        txtTitulo = findViewById(R.id.act_detalhes_evento_titulo);
        txtData = findViewById(R.id.act_detalhes_evento_data);
        txtDescricao = findViewById(R.id.act_detalhes_evento_Descricao);
        txtFacilitador = findViewById(R.id.act_detalhes_evento_Facilitador);
        txtHorario = findViewById(R.id.act_detalhes_evento_Hora);
        rvParticipantesEvento = findViewById(R.id.rcDetalhesEvento);
        btnEditarInfoEvento = findViewById(R.id.act_detalhes_evento_btnEdit);
        EventoDao.getInstance().inicializarDBHelper(getApplicationContext());
        posicaoEvento = bundleResult.getInt(ListarEventosActivity.POSICAO_EVENTO);

        setInformacoes();
        if(bundleResult.getBoolean(ListarEventosParaParticipanteActivity.ORIGEM_PARTICIPANTE)){
            btnEditarInfoEvento.setEnabled(false);
        }
        adapter = new ListaParticpanteAdapter(EventoDao.getInstance().getEventos().get(posicaoEvento).getParticipantes());
        rvParticipantesEvento.setLayoutManager(new LinearLayoutManager(this));
        rvParticipantesEvento.setAdapter(adapter);


        adapter.setOnParticipanteClickListener(new ListaParticpanteAdapter.OnParticipanteClickListener() {
            @Override
            public void onParticipanteClick(View view, int position) {
                int i = ParticipanteDao.getInstance().getParticipantes()
                        .indexOf(EventoDao.getInstance().getEventos().get(posicaoEvento).getParticipantes().get(position));
                Intent intent = new Intent(DetalhesEventoActivity.this, AtualizarPessoaActivity.class);
                intent.putExtra(MainActivity.POSICAO_PARTICIPANTE, i);
                startActivity(intent);
            }

            @Override
            public void onLongParticipanteClick(View view, int position) {
                //ParticipanteDao.getInstance().removeParticipanteDoEvento(
                 //       EventoDao.getInstance().getEventos().get(posicaoEvento),
  //                      EventoDao.getInstance().getEventos().get(posicaoEvento).getParticipantes().get(position));
//
                EventoDao.getInstance().getEventos().get(posicaoEvento).getParticipantes().remove(position);
                adapter.notifyDataSetChanged();
            }
        });
        btnEditarInfoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalhesEventoActivity.this,EditarEventoActivity.class);
                intent.putExtra(DetalhesEventoActivity.POSICAO_EVENTO, posicaoEvento);
                startActivityForResult(intent, REQUEST_ATUALIZAR_EVENTO);
            }
        });
    }

    private void setInformacoes() {
        txtTitulo.setText(EventoDao.getInstance().getEventos().get(posicaoEvento).getTitulo());
        txtHorario.setText(EventoDao.getInstance().getEventos().get(posicaoEvento).getHora());
        txtFacilitador.setText(EventoDao.getInstance().getEventos().get(posicaoEvento).getFacilitador());
        txtDescricao.setText(EventoDao.getInstance().getEventos().get(posicaoEvento).getDescricao());
        txtData.setText(EventoDao.getInstance().getEventos().get(posicaoEvento).getDia());
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == DetalhesEventoActivity.REQUEST_ATUALIZAR_EVENTO && resultCode== Activity.RESULT_OK && data != null){
            setInformacoes();
        }
    }
}
