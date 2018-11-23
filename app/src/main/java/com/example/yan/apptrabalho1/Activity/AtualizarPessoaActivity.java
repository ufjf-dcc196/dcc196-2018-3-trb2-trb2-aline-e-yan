package com.example.yan.apptrabalho1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yan.apptrabalho1.Adapter.ListaEventoAdapter;
import com.example.yan.apptrabalho1.Adapter.ListaMeusEventoAdapter;
import com.example.yan.apptrabalho1.Modelo.Participante;
import com.example.yan.apptrabalho1.Persistence.EventoDao;
import com.example.yan.apptrabalho1.Persistence.ParticipanteDao;
import com.example.yan.apptrabalho1.R;

public class AtualizarPessoaActivity extends AppCompatActivity {
    private static final int REQUEST_CADASTRAR_EVENTO_PARTICIPANTE = 1;
    public static final String POSICAO_PARTICIPANTE = "Posição Participante";
    public static final String POSICAO_EVENTO = "Posição Evento";

    private RecyclerView rvMeusEventos;
    private EditText nome;
    private EditText email;
    private EditText cpf;
    private EditText matricula;
    private Button btnSalvar;
    private Button btnNovoEvento;
    private Button btnCancelar;
    private ListaMeusEventoAdapter adapter;
    private int posicaoParticipante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_pessoa);
        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        posicaoParticipante = bundleResult.getInt(MainActivity.POSICAO_PARTICIPANTE);
        EventoDao.getInstance().inicializarDBHelper(getApplicationContext());

        rvMeusEventos = findViewById(R.id.rv_meus_eventos);
        rvMeusEventos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListaMeusEventoAdapter(ParticipanteDao.getInstance().getParticipantes().get(posicaoParticipante).getMeusEventos());
        rvMeusEventos.setAdapter(adapter);

        nome = findViewById(R.id.act_Att_participante_nome);
        email = findViewById(R.id.act_Att_participante_email);
        cpf = findViewById(R.id.act_Att_participante_cpf);
        matricula = findViewById(R.id.act_Att_participante_matricula);
        btnSalvar = findViewById(R.id.act_Att_participante_btnSalvar);
        btnNovoEvento = findViewById(R.id.act_Att_participante_btnNovoEvento);
        btnCancelar = findViewById(R.id.act_Att_participante_btnCancelar);

        nome.setText(ParticipanteDao.getInstance().getParticipantes().get(posicaoParticipante).getNome());
        email.setText(ParticipanteDao.getInstance().getParticipantes().get(posicaoParticipante).getEmail());
        cpf.setText(ParticipanteDao.getInstance().getParticipantes().get(posicaoParticipante).getCpf());
        matricula.setText(ParticipanteDao.getInstance().getParticipantes().get(posicaoParticipante).getMatricula());

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent attPart = new Intent();
                ParticipanteDao.getInstance().getParticipantes().get(posicaoParticipante).
                        setNome(nome.getText().toString()).
                        setMatricula(matricula.getText().toString()).
                        setEmail(email.getText().toString()).
                        setCpf(cpf.getText().toString());
                setResult(Activity.RESULT_OK, attPart);
                finish();
            }
        });

        btnNovoEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent attPart = new Intent(AtualizarPessoaActivity.this, ListarEventosParaParticipanteActivity.class);
                attPart.putExtra(AtualizarPessoaActivity.POSICAO_PARTICIPANTE, posicaoParticipante);
                startActivityForResult(attPart, REQUEST_CADASTRAR_EVENTO_PARTICIPANTE);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adapter.setOnMeusEventosClickListener(new ListaMeusEventoAdapter.OnMeusEventosClickListener() {
            @Override
            public void onMeusEventosClick(View view, int position) {
                Intent intent = new Intent(AtualizarPessoaActivity.this, InscritosActivity.class);
                intent.putExtra(AtualizarPessoaActivity.POSICAO_EVENTO, position);
                intent.putExtra(AtualizarPessoaActivity.POSICAO_PARTICIPANTE, posicaoParticipante);
                startActivity(intent);
            }

            @Override
            public void onLongMeusEventosClick(View view, int position) {
                int indiceEvento = EventoDao.getInstance().getIndiceEvento(ParticipanteDao.getInstance().getParticipantes().get(posicaoParticipante).getMeusEventos().get(position));
                ParticipanteDao.getInstance().getParticipantes().get(posicaoParticipante).getMeusEventos().remove(position);
                EventoDao.getInstance().getEventos().get(indiceEvento).removeParticipante(ParticipanteDao.getInstance().getParticipantes().get(posicaoParticipante));
                adapter.notifyDataSetChanged();
            }
        });

    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == AtualizarPessoaActivity.REQUEST_CADASTRAR_EVENTO_PARTICIPANTE && resultCode== Activity.RESULT_OK && data != null){
             adapter.notifyDataSetChanged();
        }
    }
}
