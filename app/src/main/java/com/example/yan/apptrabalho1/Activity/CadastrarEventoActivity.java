package com.example.yan.apptrabalho1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        nomeEvento = findViewById(R.id.act_att_Evento_Nome);
        dataEvento = findViewById(R.id.act_att_evento_Data);
        horarioEvento = findViewById(R.id.act_att_evento_Horario);
        facilitadorEvento = findViewById(R.id.act_att_evento_Facilitador);
        descricaoEvento = findViewById(R.id.act_att_evento_Descricao);
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
                if("".equals(nomeEvento.getText().toString()) || nomeEvento.getText() ==null
                        || descricaoEvento.getText().toString().equals("") || descricaoEvento.getText()==null
                        || dataEvento.getText().toString().equals("") || dataEvento.getText()==null
                        || facilitadorEvento.getText().toString().equals("") || facilitadorEvento.getText()==null
                        || horarioEvento.getText().toString().equals("") || horarioEvento.getText()==null) {
                   Toast t = Toast.makeText(getApplicationContext(), "Favor preencher todos os campos", Toast.LENGTH_LONG);
                   t.setGravity(Gravity.CENTER, 0,0);
                   t.show();
                }else {

                    setResult(Activity.RESULT_OK, intentCadEvent);
                    finish();
                }
            }
        });
    }
}
