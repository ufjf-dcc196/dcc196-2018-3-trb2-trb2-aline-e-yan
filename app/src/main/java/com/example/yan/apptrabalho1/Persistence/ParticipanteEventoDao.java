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
        /*if(!feito){
            insercaoBanco();
            feito = true;
        }*/
    }
    public ArrayList<Participante> getEventoParticipantes(int id) {
        cursor = getAllParticipantesEventosBanco(id,"Participante");
        ArrayList<Participante> participantes = new ArrayList<>();

        int indexNomeParticipante = cursor.getColumnIndexOrThrow(SemanaContract.ParticipanteBD.COLUMN_NAME_NOME);
        int indexCpfParticipante = cursor.getColumnIndexOrThrow(SemanaContract.ParticipanteBD.COLUMN_NAME_CPF);
        int indexEmailParticipante = cursor.
                getColumnIndexOrThrow(SemanaContract.ParticipanteBD.COLUMN_NAME_EMAIL);
        int indexMatriculaParticipante = cursor.
                getColumnIndexOrThrow(SemanaContract.ParticipanteBD.COLUMN_NAME_MATRICULA);
        int indexIdParticipante = cursor.getColumnIndexOrThrow(SemanaContract.ParticipanteBD._ID);

        cursor.moveToFirst();
        while (cursor.moveToNext()){
            Participante temp = new Participante();
            temp.setNome(cursor.getString(indexNomeParticipante))
                    .setCpf(cursor.getString(indexCpfParticipante))
                    .setEmail(cursor.getString(indexEmailParticipante))
                    .setMatricula(cursor.getString(indexMatriculaParticipante))
                    .setId(Integer.parseInt(cursor.getString(indexIdParticipante)));
            participantes.add(temp);
        }
        return participantes;
    }
    public void removerAllParticipantesEvento(int id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("EventoParticipante","ID_EVENTO=? "
                ,new String[]{String.valueOf(id)});

    }

    public ArrayList<Evento> getParticipanteEventos(int id) {
        cursor = getAllParticipantesEventosBanco(id,"Evento");
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


    public void addPartipanteEvento(int idEvento, Integer idParticipante){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(SemanaContract.EventoParticipanteBD.COLUMN_NAME_ID_EVENTO, idEvento);
        valores.put(SemanaContract.EventoParticipanteBD.COLUMN_NAME_ID_PARTICIPANTE, idParticipante);
        db.insert(SemanaContract.EventoParticipanteBD.TABLE_NAME,null, valores);
    }
    public void removeParticipanteEvento(int idEvento, int idParticipante){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("EventoParticipante","ID_EVENTO=? and ID_PARTICIPANTE = ?"
                ,new String[]{String.valueOf(idEvento), String.valueOf(idParticipante)});

    }
    private Cursor getAllParticipantesEventosBanco(int id, String argumento) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c;
        if("Participante".equals(argumento)){
            String MY_QUERY =
                    "SELECT * FROM "+
                            SemanaContract.EventoParticipanteBD.TABLE_NAME+" as  EvPart INNER JOIN" +
                            " " +SemanaContract.ParticipanteBD.TABLE_NAME+
                            " as Part ON EvPart.ID_EVENTO = Part._ID WHERE EvPart.ID_EVENTO=?";

            c= db.rawQuery(MY_QUERY, new String[]{String.valueOf(id)});
        }else{
            String MY_QUERY =
                    "SELECT * FROM "+
                            SemanaContract.EventoParticipanteBD.TABLE_NAME+" as  EvPart INNER JOIN" +
                            " " +SemanaContract.EventoBD.TABLE_NAME+
                            " as Ev ON EvPart.ID_EVENTO = Ev._ID WHERE EvPart.ID_PARTICIPANTE=?";

            c= db.rawQuery(MY_QUERY, new String[]{String.valueOf(id)});
        }
        Log.i("SQLTEST", "getCursorSeriado: "+c.getCount());
        return c;
    }
    public ArrayList<Evento> getEventosNaoInscritos(int idParticipante){
        Cursor cursor = getEventosNaoInscritosBanco(idParticipante);
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

    private Cursor getEventosNaoInscritosBanco(int idParticipante) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor;
        String MY_QUERY =
                "SELECT * FROM "+SemanaContract.EventoParticipanteBD.TABLE_NAME
                        +" as  EvPart inner join "+SemanaContract.EventoBD.TABLE_NAME+
                        " as Ev WHERE EvPart.ID_PARTICIPANTE !=?";

        cursor= db.rawQuery(MY_QUERY, new String[]{String.valueOf(idParticipante)});
        Log.i("SQLTEST", "getCursorSeriado: "+cursor.getCount());

        return cursor;
    }

}
