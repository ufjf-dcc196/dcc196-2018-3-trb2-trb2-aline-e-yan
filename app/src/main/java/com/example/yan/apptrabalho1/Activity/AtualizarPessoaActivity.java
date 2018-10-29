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
import com.example.yan.apptrabalho1.Modelo.Participante;
import com.example.yan.apptrabalho1.Persistence.ParticipanteDao;
import com.example.yan.apptrabalho1.R;

public class AtualizarPessoaActivity extends AppCompatActivity {
    private RecyclerView rvMeusEventos;
    private EditText nome;
    private EditText email;
    private EditText cpf;
    private EditText matricula;
    private Button btnSalvar;
    private Button btnNovoEvento;
    private Button btnCancelar;
    private ListaEventoAdapter adapter;
    private int posicaoParticipante;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_pessoa);
        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        posicaoParticipante = bundleResult.getInt(MainActivity.POSICAO_PARTICIPANTE);

        Participante p = ParticipanteDao.getInstance().getParticipantes().get(posicaoParticipante);

        rvMeusEventos = findViewById(R.id.rv_meus_eventos);
        rvMeusEventos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ListaEventoAdapter(p.getMeusEventos(), true);
        rvMeusEventos.setAdapter(adapter);

        nome = findViewById(R.id.act_Att_participante_nome);
        email = findViewById(R.id.act_Att_participante_email);
        cpf = findViewById(R.id.act_Att_participante_cpf);
        matricula = findViewById(R.id.act_Att_participante_matricula);
        btnSalvar = findViewById(R.id.act_Att_participante_btnSalvar);
        btnNovoEvento = findViewById(R.id.act_Att_participante_btnNovoEvento);
        btnCancelar = findViewById(R.id.act_Att_participante_btnCancelar);

        nome.setText(p.getNome());
        email.setText(p.getEmail());
        cpf.setText(p.getCpf());
        matricula.setText(p.getMatricula());

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
                Intent attPart = new Intent();
                //abrir janela novo evento
                setResult(Activity.RESULT_OK, attPart);
                finish();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent attPart = new Intent();
                setResult(Activity.RESULT_OK, attPart);
                finish();
            }
        });
    }
}
