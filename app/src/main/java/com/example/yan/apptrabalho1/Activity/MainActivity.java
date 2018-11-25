package com.example.yan.apptrabalho1.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.yan.apptrabalho1.Adapter.ListaParticpanteAdapter;
import com.example.yan.apptrabalho1.Banco.SemanaDBHelper;
import com.example.yan.apptrabalho1.Modelo.Evento;
import com.example.yan.apptrabalho1.Modelo.Participante;
import com.example.yan.apptrabalho1.Persistence.EventoDao;
import com.example.yan.apptrabalho1.Persistence.ParticipanteDao;
import com.example.yan.apptrabalho1.Persistence.ParticipanteEventoDao;
import com.example.yan.apptrabalho1.R;

public class MainActivity extends AppCompatActivity {

     private RecyclerView rvMain;
     private Button cadparticipante, cadevento, listeventos;

    private ListaParticpanteAdapter particpanteAdapter;

    private static final int REQUEST_CADASTRAR_EVENTO = 1;
    private static final int REQUEST_CADASTRAR_PARTICPANTE=2;
    private static final int RESQUEST_DETALHES_PESSOA= 3;



    public static  final String NOME_PARTICPANTE ="NOME";
    public static  final String EMAIL_PARTICPANTE="EMAIL";
    public static  final String CPF_PARTICPANTE="CPF";
    public static  final String MATRICULA_PARTICPANTE="MATRICULA";

    public static  final String TITULO_EVENTO ="TITULO";
    public static  final String DESCRICAO_EVENTO="DESCRICAO";
    public static  final String FACILITADOR="FACILITADOR";
    public static  final String DATA="DATA";
    public static  final String HORA="HORA";

    public static final String ID_PARTICIPANTE = "Posição Participante";
    private SemanaDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        dbHelper = new SemanaDBHelper(getApplicationContext());
        ParticipanteDao.getInstance().inicializarDBHelper(getApplicationContext());
        rvMain =(RecyclerView)findViewById(R.id.recprincipal);

        rvMain.setLayoutManager(new LinearLayoutManager(this));

        particpanteAdapter = new ListaParticpanteAdapter(ParticipanteDao.getInstance().getParticipantes());

//        particpanteAdapter.setCursor(getAllParticipantesBanco());
        rvMain.setAdapter(particpanteAdapter);

        cadparticipante=(Button)findViewById(R.id.btncadastrarpessoa);

        particpanteAdapter.setOnParticipanteClickListener(new ListaParticpanteAdapter.OnParticipanteClickListener() {
            @Override
            public void onParticipanteClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, AtualizarPessoaActivity.class);
                int idParticipante = ParticipanteDao.getInstance().getParticipantes().get(position).getId();
                intent.putExtra(MainActivity.ID_PARTICIPANTE, idParticipante);

                startActivityForResult(intent, RESQUEST_DETALHES_PESSOA);
            }

            @Override
            public void onLongParticipanteClick(View view, int position) {
                //  EventoDao.getInstance().removeParticipanteEvento(ParticipanteDao.getInstance().getParticipantes().get(position));

                ParticipanteDao.getInstance().removeParticipante(ParticipanteDao.getInstance().getParticipantes().get(position));
                particpanteAdapter.setParticipantes(ParticipanteDao.getInstance().getParticipantes());
                particpanteAdapter.notifyItemRemoved(position);
            }

        });

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
                startActivity(intent);
            }
        });



        }


    @Override
    protected void onResume() {
        super.onResume();
        particpanteAdapter.setParticipantes(ParticipanteDao.getInstance().getParticipantes());
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
            //addParticipante(p);
            ParticipanteDao.getInstance().addParticipante(p);
        }else if(requestCode == MainActivity.REQUEST_CADASTRAR_EVENTO && resultCode== Activity.RESULT_OK && data != null){

            Bundle bundleResultado = data.getExtras();
            Evento e = new Evento();
            e.setDescricao(bundleResultado.getString(MainActivity.DESCRICAO_EVENTO)).
                    setDia(bundleResultado.getString(MainActivity.DATA)).
                    setFacilitador(bundleResultado.getString(MainActivity.FACILITADOR)).
                    setHora(bundleResultado.getString(MainActivity.HORA)).
                    setTitulo(bundleResultado.getString(MainActivity.TITULO_EVENTO));
            EventoDao.getInstance().addEvento(e);
            int idEvento = EventoDao.getInstance().getIndiceEvento(e);
            ParticipanteEventoDao.getInstance().inicializarDBHelper(getApplicationContext());
            ParticipanteEventoDao.getInstance().addPartipanteEvento(idEvento, null);
        }
    }
}
