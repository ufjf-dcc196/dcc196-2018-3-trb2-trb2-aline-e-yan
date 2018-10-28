package com.example.yan.apptrabalho1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yan.apptrabalho1.R;

public class CadastrarEventoActivity extends AppCompatActivity {
    private EditText nomeEvento;
    private EditText dataEvento;
    private EditText horarioEvento;
    private EditText facilitadorEvento;
    private EditText descricaoEvento;
    private Button btnCadastrarEvento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_evento);
        nomeEvento = findViewById(R.id.act_cadastro_Evento_Nome);
        dataEvento = findViewById(R.id.act_cadastro_Data);
        horarioEvento = findViewById(R.id.act_cadastro_Horario);
        facilitadorEvento = findViewById(R.id.act_cadastro_Facilitador);
        descricaoEvento = findViewById(R.id.act_cadastro_Descricao);
        btnCadastrarEvento = findViewById(R.id.act_cadastro_btnOk);

        btnCadastrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCadEvent = new Intent();
                intentCadEvent.putExtra(MainActivity.TITULO_EVENTO, nomeEvento.getText().toString());
                intentCadEvent.putExtra(MainActivity.DESCRICAO_EVENTO, descricaoEvento.getText().toString());
                intentCadEvent.putExtra(MainActivity.DATA, dataEvento.getText().toString());
                intentCadEvent.putExtra(MainActivity.FACILITADOR, facilitadorEvento.getText().toString());
                intentCadEvent.putExtra(MainActivity.HORA, horarioEvento.getText().toString());
                setResult(Activity.RESULT_OK, intentCadEvent);
                finish();

            }
        });
    }
}
