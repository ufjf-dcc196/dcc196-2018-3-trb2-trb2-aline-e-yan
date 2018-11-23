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

public class ParticipanteEventoDao {
    private static ParticipanteEventoDao instance = new ParticipanteEventoDao();
    private SemanaDBHelper dbHelper;
    private Cursor cursor;
    private boolean feito = false;
    private ParticipanteEventoDao() {
    }
    public static ParticipanteEventoDao getInstance(){
        return instance;
    }

    public void insercaoBanco(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(SemanaContract.EventoBD.COLUMN_NAME_TITULO, "Evento inserido no banco 222");
        valores.put(SemanaContract.EventoBD.COLUMN_NAME_DESCRICAO, "Evento inserido no banco 333");
        valores.put(SemanaContract.EventoBD.COLUMN_NAME_DIA, "01/11/2020");
        valores.put(SemanaContract.EventoBD.COLUMN_NAME_FACILITADOR, "DE CERTO");
        valores.put(SemanaContract.EventoBD.COLUMN_NAME_HORA, "00:01");
        db.insert(SemanaContract.EventoBD.TABLE_NAME,null, valores);


    }

    public void inicializarDBHelper(Context c){
        dbHelper = new SemanaDBHelper(c);
        if(!feito){
            insercaoBanco();
            feito = true;
        }
    }
    public ArrayList<Evento> getEventos(Context c) {
        cursor = getAllEventosBanco();
        ArrayList<Evento> eventos = new ArrayList<>();
        int indexTituloEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_TITULO);
        int indexDataEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_DIA);
        int indexHoraEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_HORA);
        int indexFacilitadorEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_FACILITADOR);
        int indexDescricaoEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_DESCRICAO);
        int indexIdEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD._ID);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            Evento temp = new Evento();
            temp.setTitulo(cursor.getString(indexTituloEvento))
                    .setDia(cursor.getString(indexDataEvento))
                    .setHora(cursor.getString(indexHoraEvento))
                    .setFacilitador(cursor.getString(indexFacilitadorEvento))
                    .setDescricao(cursor.getString(indexDescricaoEvento))
                    .setId(Integer.parseInt(cursor.getString(indexIdEvento)));
            eventos.add(temp);
        }
        return eventos;
    }
    public void addEvento(Evento e){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(SemanaContract.EventoBD.COLUMN_NAME_TITULO, e.getTitulo());
        valores.put(SemanaContract.EventoBD.COLUMN_NAME_DESCRICAO, e.getDescricao());
        valores.put(SemanaContract.EventoBD.COLUMN_NAME_DIA, e.getDia());
        valores.put(SemanaContract.EventoBD.COLUMN_NAME_FACILITADOR, e.getFacilitador());
        valores.put(SemanaContract.EventoBD.COLUMN_NAME_HORA, e.getHora());
        db.insert(SemanaContract.EventoBD.TABLE_NAME,null, valores);

    }
    public void removeEvento(Evento e){
        //remover do banco
    }
    public int getIndiceEvento(Evento e){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                SemanaContract.EventoBD.COLUMN_NAME_TITULO,
                SemanaContract.EventoBD._ID
        };
        String sort = SemanaContract.EventoBD.COLUMN_NAME_DIA+ " DESC";

        Cursor c = db.query(SemanaContract.EventoBD.TABLE_NAME, visao,
                "Where TITULO= "+e.getTitulo(),null,
                null,null, sort);
        Log.i("SQLTEST", "getCursorSeriado: "+c.getCount());
        if(c.moveToFirst()){
            return c.getInt(1);
        }
        return -1;
    }

    public void removeParticipanteEvento(int idParticipante, int idEvento){

    }
    private Cursor getAllEventosBanco() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                SemanaContract.EventoBD.COLUMN_NAME_TITULO,
                SemanaContract.EventoBD.COLUMN_NAME_DESCRICAO,
                SemanaContract.EventoBD.COLUMN_NAME_DIA,
                SemanaContract.EventoBD.COLUMN_NAME_FACILITADOR,
                SemanaContract.EventoBD.COLUMN_NAME_HORA,
                SemanaContract.EventoBD._ID
        };
        String sort = SemanaContract.EventoBD.COLUMN_NAME_DIA+ " DESC";
        Cursor c = db.query(SemanaContract.EventoBD.TABLE_NAME, visao,
                null,null,null,null, sort);
        Log.i("SQLTEST", "getCursorSeriado: "+c.getCount());
        return c;
    }

}
