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

public class CadastrarParticipante extends AppCompatActivity {
    private EditText nome;
    private EditText email;
    private EditText cpf;
    private EditText matricula;
    private Button btnOk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_participante);
        nome = findViewById(R.id.text_Nome);
        btnOk = findViewById(R.id.test_btnOk);
        email = findViewById(R.id.text_Email);
        cpf = findViewById(R.id.text_CPF);
        matricula = findViewById(R.id.text_Matricula);


        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCadPart = new Intent();
                intentCadPart.putExtra(MainActivity.NOME_PARTICPANTE, nome.getText().toString());
                intentCadPart.putExtra(MainActivity.EMAIL_PARTICPANTE, email.getText().toString());
                intentCadPart.putExtra(MainActivity.CPF_PARTICPANTE, cpf.getText().toString());
                intentCadPart.putExtra(MainActivity.MATRICULA_PARTICPANTE, matricula.getText().toString());
                if("".equals(nome.getText().toString()) || nome.getText() ==null
                        || email.getText().toString().equals("") || email.getText()==null
                        || cpf.getText().toString().equals("") || cpf.getText()==null
                        || matricula.getText().toString().equals("") || matricula.getText()==null) {
                    Toast t = Toast.makeText(getApplicationContext(), "Favor preencher todos os campos", Toast.LENGTH_LONG);
                    t.setGravity(Gravity.CENTER, 0,0);
                    t.show();

                }else{
                    setResult(Activity.RESULT_OK, intentCadPart);

                    finish();
                }
            }
        });

    }
}
