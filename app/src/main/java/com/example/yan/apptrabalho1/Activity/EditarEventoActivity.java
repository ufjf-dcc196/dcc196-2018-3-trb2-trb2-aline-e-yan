package com.example.yan.apptrabalho1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yan.apptrabalho1.Modelo.Evento;
import com.example.yan.apptrabalho1.Persistence.EventoDao;
import com.example.yan.apptrabalho1.R;

public class EditarEventoActivity extends AppCompatActivity {
    private EditText nomeEvento;
    private EditText dataEvento;
    private EditText horarioEvento;
    private EditText facilitadorEvento;
    private EditText descricaoEvento;
    private Button btnOk;
    private int idEvento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_evento);
        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        idEvento = bundleResult.getInt(DetalhesEventoActivity.ID_EVENTO);
        nomeEvento = findViewById(R.id.act_att_Evento_Nome);
        dataEvento = findViewById(R.id.act_att_evento_Data);
        horarioEvento = findViewById(R.id.act_att_evento_Horario);
        facilitadorEvento = findViewById(R.id.act_att_evento_Facilitador);
        descricaoEvento = findViewById(R.id.act_att_evento_Descricao);
        EventoDao.getInstance().inicializarDBHelper(getApplicationContext());
        btnOk = findViewById(R.id.act_att_evento_btnOk);
        setInformacoes();
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Evento e = new Evento();
                e.setDescricao(descricaoEvento.getText().toString()).
                        setDia(dataEvento.getText().toString()).
                        setFacilitador(facilitadorEvento.getText().toString()).
                        setHora(horarioEvento.getText().toString()).
                        setTitulo(nomeEvento.getText().toString());
                EventoDao.getInstance().atualizarEvento(e);
                ListarEventosActivity.attRecycle();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
    private void setInformacoes() {
        nomeEvento.setText(EventoDao.getInstance().getEventoById(idEvento).getTitulo());
        horarioEvento.setText(EventoDao.getInstance().getEventoById(idEvento).getHora());
        facilitadorEvento.setText(EventoDao.getInstance().getEventoById(idEvento).getFacilitador());
        descricaoEvento.setText(EventoDao.getInstance().getEventoById(idEvento).getDescricao());
        dataEvento.setText(EventoDao.getInstance().getEventoById(idEvento).getDia());
    }
}