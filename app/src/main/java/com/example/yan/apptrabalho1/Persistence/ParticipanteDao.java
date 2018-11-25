package com.example.yan.apptrabalho1.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yan.apptrabalho1.Banco.SemanaContract;
import com.example.yan.apptrabalho1.Banco.SemanaDBHelper;
import com.example.yan.apptrabalho1.Modelo.Evento;
import com.example.yan.apptrabalho1.Modelo.Participante;

import java.util.ArrayList;
import java.util.List;

public class ParticipanteDao {
    private static ParticipanteDao instance = new ParticipanteDao();
    private SemanaDBHelper dbHelper;
    private Cursor cursor;
    private boolean feito = false;

    private ParticipanteDao() {

//        EventoDao.getInstance().getEventos().get(0).addParticipante(participantes.get(0));
  //      EventoDao.getInstance().getEventos().get(0).addParticipante(participantes.get(1));
  //      EventoDao.getInstance().getEventos().get(0).addParticipante(participantes.get(2));
    }
    public static ParticipanteDao getInstance(){
        return  instance;
    }


    public void insercaoBanco(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(SemanaContract.ParticipanteBD.COLUMN_NAME_NOME, "Aline de Paula Sotte");
        valores.put(SemanaContract.ParticipanteBD.COLUMN_NAME_CPF, "111.444.555-08");
        valores.put(SemanaContract.ParticipanteBD.COLUMN_NAME_EMAIL, "Aline@aline.com.br");
        valores.put(SemanaContract.ParticipanteBD.COLUMN_NAME_MATRICULA, "201476001");
        db.insert(SemanaContract.ParticipanteBD.TABLE_NAME,null, valores);
   }

    public void inicializarDBHelper(Context c){
        dbHelper = new SemanaDBHelper(c);
        /*if(!feito){
            insercaoBanco();
            feito = true;
        }*/
    }

    public ArrayList<Participante> getParticipantes() {
        ArrayList<Participante> participantes = new ArrayList<>();
        cursor = getAllParticipantesBanco();
        int indexNomeParticipante = cursor.getColumnIndexOrThrow(SemanaContract.ParticipanteBD.COLUMN_NAME_NOME);
        int indexCpfParticipante = cursor.getColumnIndexOrThrow(SemanaContract.ParticipanteBD.COLUMN_NAME_CPF);
        int indexEmailParticipante = cursor.
                getColumnIndexOrThrow(SemanaContract.ParticipanteBD.COLUMN_NAME_EMAIL);
        int indexMatriculaParticipante = cursor.
                getColumnIndexOrThrow(SemanaContract.ParticipanteBD.COLUMN_NAME_MATRICULA);
        int indexIdParticipante = cursor.getColumnIndexOrThrow(SemanaContract.ParticipanteBD._ID);
        if(cursor.moveToFirst()){
        do{
            Participante temp = new Participante();
            temp.setNome(cursor.getString(indexNomeParticipante))
                    .setCpf(cursor.getString(indexCpfParticipante))
                    .setEmail(cursor.getString(indexEmailParticipante))
                    .setMatricula(cursor.getString(indexMatriculaParticipante))
                    .setId(Integer.parseInt(cursor.getString(indexIdParticipante)));
            participantes.add(temp);
        }while (cursor.moveToNext());
        }
        return participantes;
    }

    public void addParticipante(Participante p){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(SemanaContract.ParticipanteBD.COLUMN_NAME_NOME, p.getNome());
        valores.put(SemanaContract.ParticipanteBD.COLUMN_NAME_CPF, p.getCpf());
        valores.put(SemanaContract.ParticipanteBD.COLUMN_NAME_EMAIL, p.getEmail());
        valores.put(SemanaContract.ParticipanteBD.COLUMN_NAME_MATRICULA, p.getMatricula());
        db.insert(SemanaContract.ParticipanteBD.TABLE_NAME,null, valores);
   }
//    public void removeParticipante(int indice){
    public void removeParticipante(Participante indice){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows=db.delete("TABLE_NAME","_ID=?",new String[]{String.valueOf(indice.getId())});

    }
//    public void removeAllParticipanteEvento(Evento e)
    public void removeAllParticipanteEvento(Evento e,Participante participantes){
           e.removeParticipante(participantes.getId());
    }

    private Cursor getAllParticipantesBanco() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                SemanaContract.ParticipanteBD.COLUMN_NAME_NOME,
                SemanaContract.ParticipanteBD.COLUMN_NAME_CPF,
                SemanaContract.ParticipanteBD.COLUMN_NAME_EMAIL,
                SemanaContract.ParticipanteBD.COLUMN_NAME_MATRICULA,
                SemanaContract.ParticipanteBD._ID
        };
        String sort = SemanaContract.ParticipanteBD.COLUMN_NAME_NOME+ " DESC";
        Cursor c = db.query(SemanaContract.ParticipanteBD.TABLE_NAME, visao,
                null,null,null,null, sort);
        Log.i("SQLTEST", "getCursorSeriado: "+c.getCount());
        return c;
    }

}
