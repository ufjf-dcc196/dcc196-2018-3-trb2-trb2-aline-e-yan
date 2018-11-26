package com.example.yan.apptrabalho1.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.yan.apptrabalho1.Banco.SemanaContract;
import com.example.yan.apptrabalho1.Banco.SemanaDBHelper;
import com.example.yan.apptrabalho1.Modelo.Evento;

import java.util.ArrayList;

public class EventoDao {
    private static EventoDao instance = new EventoDao();
    private SemanaDBHelper dbHelper;
    private Cursor cursor;
    private boolean feito = false;
    private EventoDao() {
    }
    public static EventoDao getInstance(){
        return instance;
    }

    private void insercaoBanco(){
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
        /*if(!feito){
            insercaoBanco();
            ParticipanteEventoDao.getInstance().inicializarDBHelper(c);
            ParticipanteEventoDao.getInstance().addPartipanteEvento(1,1);
            feito = true;
        }*/
    }
    public Evento getEventoById(int idEvento){
        int indexTituloEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_TITULO);
        int indexDataEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_DIA);
        int indexHoraEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_HORA);
        int indexFacilitadorEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_FACILITADOR);
        int indexDescricaoEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_DESCRICAO);
        int indexIdEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD._ID);
        Evento eventoSolicitado = null;
        if(cursor.moveToFirst()) {
            eventoSolicitado = new Evento();
            eventoSolicitado.setTitulo(cursor.getString(indexTituloEvento))
                    .setDia(cursor.getString(indexDataEvento))
                    .setHora(cursor.getString(indexHoraEvento))
                    .setFacilitador(cursor.getString(indexFacilitadorEvento))
                    .setDescricao(cursor.getString(indexDescricaoEvento))
                    .setId(Integer.parseInt(cursor.getString(indexIdEvento)));
        }
        return eventoSolicitado;
    }
    public void atualizarEvento(Evento aux) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Titulo", aux.getTitulo());
        cv.put("DIA", aux.getDia());
        cv.put("HORA",aux.getHora());
        cv.put("FACILITADOR", aux.getFacilitador());
        cv.put("DESCRICAO", aux.getDescricao());
        db.update("Evento",cv,
                "_ID=?",new String[]{String.valueOf(aux.getId())});
    }
    public ArrayList<Evento> getEventos() {
        cursor = getAllEventosBanco();
        ArrayList<Evento> eventos = new ArrayList<>();
        int indexTituloEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_TITULO);
        int indexDataEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_DIA);
        int indexHoraEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_HORA);
        int indexFacilitadorEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_FACILITADOR);
        int indexDescricaoEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD.COLUMN_NAME_DESCRICAO);
        int indexIdEvento = cursor.getColumnIndexOrThrow(SemanaContract.EventoBD._ID);
        if(cursor.moveToFirst()){
        do{
            Evento temp = new Evento();
            temp.setTitulo(cursor.getString(indexTituloEvento))
                    .setDia(cursor.getString(indexDataEvento))
                    .setHora(cursor.getString(indexHoraEvento))
                    .setFacilitador(cursor.getString(indexFacilitadorEvento))
                    .setDescricao(cursor.getString(indexDescricaoEvento))
                    .setId(Integer.parseInt(cursor.getString(indexIdEvento)));
            eventos.add(temp);
        }while (cursor.moveToNext());
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
    public void removeEvento(Evento e) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows=db.delete(SemanaContract.EventoBD.TABLE_NAME,
                "_ID=?",new String[]{String.valueOf(e.getId())});
        }

    public int getIndiceEvento(Evento e){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                SemanaContract.EventoBD.COLUMN_NAME_TITULO,
                SemanaContract.EventoBD._ID
        };
        String sort = SemanaContract.EventoBD.COLUMN_NAME_DIA+ " DESC";

        Cursor c = db.query(SemanaContract.EventoBD.TABLE_NAME, visao,
                "TITULO = ?",new String[]{e.getTitulo()} ,
                null,null, sort);
        Log.i("SQLTEST", "getCursorSeriado: "+c.getCount());
        if(c.moveToFirst()){
            return c.getInt(1);
        }
        return -1;
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
