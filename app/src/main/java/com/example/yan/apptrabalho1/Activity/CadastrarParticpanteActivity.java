package com.example.yan.apptrabalho1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.yan.apptrabalho1.R;

public class CadastrarParticpanteActivity extends AppCompatActivity {

    private EditText nome, email, cpf, matricula;
    private Button btnconfirma;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_pessoas);
        
        nome=(EditText)findViewById(R.id.editcpnome);
        email=(EditText)findViewById(R.id.editcpemail);
        cpf=(EditText)findViewById(R.id.editcpcpf);
        matricula=(EditText)findViewById(R.id.editcpmatricula);
        
        btnconfirma=(Button)findViewById(R.id.btncadastrarpessoa);
        
        btnconfirma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_cadparticipante= new Intent();

//                intent_cadparticipante.putExtra(MainActivity.NOME_PARTICPANTE, nome.getText().toString());
//                intent_cadparticipante.putExtra(MainActivity.EMAIL_PARTICPANTE, email.getText().toString());
//                intent_cadparticipante.putExtra(MainActivity.CPF_PARTICPANTE, cpf.getText().toString());
//                intent_cadparticipante.putExtra(MainActivity.MATRICULA_PARTICPANTE, matricula.getText().toString());

                setResult(Activity.RESULT_OK,intent_cadparticipante);

                
            }
        });
        
    }
}
