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
import com.example.yan.apptrabalho1.Persistence.EventoDao;
import com.example.yan.apptrabalho1.Persistence.ParticipanteDao;
import com.example.yan.apptrabalho1.Persistence.ParticipanteEventoDao;
import com.example.yan.apptrabalho1.R;

public class DetalhesEventoActivity extends AppCompatActivity {
    public static final String ID_EVENTO = "Posiçao do Evento";
    private static final int REQUEST_ATUALIZAR_EVENTO=1;

    private RecyclerView rvParticipantesEvento;
    private ListaParticpanteAdapter adapter;
    private Button btnEditarInfoEvento;
    private TextView txtTitulo,txtData,txtHorario,txtFacilitador,txtDescricao;
    private int idEvento;

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
        ParticipanteEventoDao.getInstance().inicializarDBHelper(getApplicationContext());

        idEvento = bundleResult.getInt(ListarEventosActivity.ID_EVENTO);

        setInformacoes();
        if(bundleResult.getBoolean(ListarEventosParaParticipanteActivity.ORIGEM_PARTICIPANTE)){
            btnEditarInfoEvento.setEnabled(false);
        }
        adapter = new ListaParticpanteAdapter(
                ParticipanteEventoDao.getInstance().getEventoParticipantes(idEvento)
        );
        rvParticipantesEvento.setLayoutManager(new LinearLayoutManager(this));
        rvParticipantesEvento.setAdapter(adapter);


        adapter.setOnParticipanteClickListener(new ListaParticpanteAdapter.OnParticipanteClickListener() {
            @Override
            public void onParticipanteClick(View view, int position) {
                int idParticipante = getIdParticipante(position);
                Intent intent = new Intent(DetalhesEventoActivity.this, AtualizarPessoaActivity.class);
                intent.putExtra(MainActivity.ID_PARTICIPANTE, idParticipante);
                startActivity(intent);
            }

            @Override
            public void onLongParticipanteClick(View view, int position) {
                int idParticipante = getIdParticipante(position);
                ParticipanteEventoDao.getInstance().removeParticipanteEvento(idEvento,idParticipante);
                adapter.setParticipantes(
                        ParticipanteEventoDao.getInstance().getEventoParticipantes(idEvento)
                );
                adapter.notifyItemRemoved(position);
            }
        });
        btnEditarInfoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetalhesEventoActivity.this,EditarEventoActivity.class);
                intent.putExtra(DetalhesEventoActivity.ID_EVENTO, idEvento);
                startActivityForResult(intent, REQUEST_ATUALIZAR_EVENTO);
            }
        });
    }

    private void setInformacoes() {
        if(EventoDao.getInstance().getEventoById(idEvento) != null){
            txtTitulo.setText(
                EventoDao.getInstance().getEventoById(idEvento).getTitulo());
            txtHorario.setText(
                    EventoDao.getInstance().getEventoById(idEvento).getHora());
            txtFacilitador.setText(
                    EventoDao.getInstance().getEventoById(idEvento).getFacilitador());
            txtDescricao.setText(
                    EventoDao.getInstance().getEventoById(idEvento).getDescricao());
            txtData.setText(
                    EventoDao.getInstance().getEventoById(idEvento).getDia());
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == DetalhesEventoActivity.REQUEST_ATUALIZAR_EVENTO && resultCode== Activity.RESULT_OK && data != null){

            setInformacoes();
        }
    }
    private int getIdParticipante(int position){
        int idParticipante = ParticipanteDao.getInstance()
                .getParticipantes().get(position).getId();
        return idParticipante;
    }
}
