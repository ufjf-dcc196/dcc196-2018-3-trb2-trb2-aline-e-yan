package com.example.yan.apptrabalho1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.yan.apptrabalho1.R;

public class MainActivity extends AppCompatActivity {

     private RecyclerView mainprincipal;
     private Button cadparticipante, cadevento, listeventos,detalpessoas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainprincipal=(RecyclerView)findViewById(R.id.recprincipal);

        cadparticipante=(Button)findViewById(R.id.btncadastrarpessoa);

        cadparticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent1 = new Intent(MainActivity.this,CadastrarParticpanteActivity.class);
                startActivity(intent1);

            }
        });

        cadevento=(Button)findViewById(R.id.btncadastrarevento);

        cadevento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent2= new Intent(MainActivity.this,CadastroEventoActivity.class);
             startActivity(intent2);
            }
        });

        listeventos=(Button)findViewById(R.id.btnlistaevento);
        listeventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(MainActivity.this, ListarEventosActivity.class);
                startActivity(intent3);
            }
        });

        detalpessoas=(Button)findViewById(R.id.btndetalhespessoas);
        detalpessoas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainActivity.this, DetalhesPessoasActivity.class);
                startActivity(intent4);
            }
        });





    }
}
