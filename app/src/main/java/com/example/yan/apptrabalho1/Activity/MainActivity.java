package com.example.yan.apptrabalho1.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.yan.apptrabalho1.Adapter.ListaParticpanteAdapter;
import com.example.yan.apptrabalho1.Modelo.Participante;
import com.example.yan.apptrabalho1.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

     private RecyclerView rvMain;
     private Button cadparticipante, cadevento, listeventos,detalpessoas;
     private ArrayList<Participante> participantes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        participantesCreate();

        rvMain =(RecyclerView)findViewById(R.id.recprincipal);

        rvMain.setLayoutManager(new LinearLayoutManager(this));

        final ListaParticpanteAdapter particpanteAdapter = new ListaParticpanteAdapter(participantes);



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

        rvMain.setAdapter(particpanteAdapter);



    }
    private void participantesCreate() {
        for (int i = 0 ; i< 5; i++){
            Participante p = new Participante();
            p.setCpf("123.321.123-00").setEmail("participante"+i+"@gmail.com")
                    .setMatricula("Matricula "+i).setNome("Nome Participante "+i);
            participantes.add(p);
        }
    }

}
