package com.example.yan.apptrabalho1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.yan.apptrabalho1.Adapter.ListaParticpanteAdapter;
import com.example.yan.apptrabalho1.Modelo.Evento;
import com.example.yan.apptrabalho1.Modelo.Participante;
import com.example.yan.apptrabalho1.Persistence.ParticipanteDao;
import com.example.yan.apptrabalho1.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

     private RecyclerView rvMain;
     private Button cadparticipante, cadevento, listeventos,detalpessoas;

    private ArrayList<Evento> eventos = new ArrayList<>();
    private ListaParticpanteAdapter particpanteAdapter;

    private static final int REQUEST_LISTAR_EVENTO = 1;
    private static final int REQUEST_CADASTRAR_EVENTO = 2;
    private  static final int REQUEST_CADASTRAR_PARTICPANTE=3;
    private static  final int RESQUEST_DETALHES_PESSOA=4;


    public static  final String NOME_PARTICPANTE ="NOME";
    public static  final String EMAIL_PARTICPANTE="EMAIL";
    public static  final String CPF_PARTICPANTE="CPF";
    public static  final String MATRICULA_PARTICPANTE="MATRICULA";

    public static  final String TITULO_EVENTO ="TITULO";
    public static  final String DESCRICAO_EVENTO="DESCRICAO";
    public static  final String FACILITADOR="FACILITADOR";
    public static  final String DATA="DATA";
    public static  final String HORA="HORA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        rvMain =(RecyclerView)findViewById(R.id.recprincipal);

        rvMain.setLayoutManager(new LinearLayoutManager(this));

        particpanteAdapter = new ListaParticpanteAdapter(ParticipanteDao.getInstance().getParticipantes());

        rvMain.setAdapter(particpanteAdapter);

        cadparticipante=(Button)findViewById(R.id.btncadastrarpessoa);
        cadparticipante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,CadastrarParticipante.class);
                startActivityForResult(intent, REQUEST_CADASTRAR_PARTICPANTE);

            }
        });

        cadevento=(Button)findViewById(R.id.btncadastrarevento);
        cadevento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             Intent intent= new Intent(MainActivity.this,CadastrarEventoActivity.class);
                startActivityForResult(intent, REQUEST_CADASTRAR_EVENTO);
            }
        });

        listeventos=(Button)findViewById(R.id.btnlistaevento);
        listeventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListarEventosActivity.class);
                startActivityForResult(intent, REQUEST_LISTAR_EVENTO);
            }
        });

        detalpessoas=(Button)findViewById(R.id.btndetalhespessoas);
        detalpessoas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetalhesPessoasActivity.class);
                startActivityForResult(intent, RESQUEST_DETALHES_PESSOA);
            }
        });


        }





    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MainActivity.REQUEST_CADASTRAR_PARTICPANTE && resultCode== Activity.RESULT_OK && data != null){
            Bundle bundleResultado = data.getExtras();
            Participante p = new Participante();
            p.setNome(bundleResultado.getString(MainActivity.NOME_PARTICPANTE)).
                    setMatricula(bundleResultado.getString(MainActivity.MATRICULA_PARTICPANTE)).
                    setCpf(bundleResultado.getString(MainActivity.CPF_PARTICPANTE)).
                    setEmail(bundleResultado.getString(MainActivity.EMAIL_PARTICPANTE));

            ParticipanteDao.getInstance().addParticipante(p);

            rvMain.setAdapter(particpanteAdapter);


        }else if(requestCode == MainActivity.REQUEST_CADASTRAR_EVENTO && resultCode== Activity.RESULT_OK && data != null){

            Bundle bundleResultado = data.getExtras();
            Evento e = new Evento();
            e.setDescricao(bundleResultado.getString(MainActivity.DESCRICAO_EVENTO)).
                    setDia(bundleResultado.getString(MainActivity.DATA)).
                    setFacilitador(bundleResultado.getString(MainActivity.FACILITADOR)).
                    setHora(bundleResultado.getString(MainActivity.HORA)).
                    setTitulo(bundleResultado.getString(MainActivity.TITULO_EVENTO));
            eventos.add(e);
            Toast.makeText(getApplicationContext(), "NOME EVENTO"+e.getDescricao(), Toast.LENGTH_SHORT).show();


        }else if(requestCode == MainActivity.REQUEST_LISTAR_EVENTO && resultCode== Activity.RESULT_OK && data != null){

            Bundle bundleResultado = data.getExtras();

        }

        else if(requestCode == MainActivity.RESQUEST_DETALHES_PESSOA && resultCode== Activity.RESULT_OK && data != null){

            Bundle bundleResultado = data.getExtras();


    }
}
}
