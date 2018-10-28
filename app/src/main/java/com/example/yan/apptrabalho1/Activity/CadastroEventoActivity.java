package com.example.yan.apptrabalho1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yan.apptrabalho1.R;

public class CadastroEventoActivity extends AppCompatActivity {
    private EditText edtTitulo;
    private EditText edtData;
    private EditText edtHora;
    private EditText edtFacilitador;
    private EditText edtDescricao;
    private Button btnOK;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        edtTitulo = findViewById(R.id.editcetitulo);
        edtData = findViewById(R.id.editcedia);
        edtHora = findViewById(R.id.editcehora);
        edtFacilitador = findViewById(R.id.editcefacilitador);
        edtDescricao = findViewById(R.id.editcedescricao);
        btnOK = findViewById(R.id.btncadastrarevento);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultado = new Intent();
                resultado.putExtra(MainActivity.TITULO_EVENTO, edtTitulo.getText().toString());
                resultado.putExtra(MainActivity.DATA, edtData.getText().toString());
                resultado.putExtra(MainActivity.HORA, edtHora.getText().toString());
                resultado.putExtra(MainActivity.FACILITADOR, edtFacilitador.getText().toString());
                resultado.putExtra(MainActivity.DESCRICAO_EVENTO, edtDescricao.getText().toString());
                setResult(Activity.RESULT_OK, resultado);
                finish();
            }
        });
    }
}
