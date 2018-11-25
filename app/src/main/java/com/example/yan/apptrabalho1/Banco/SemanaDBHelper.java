package com.example.yan.apptrabalho1.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SemanaDBHelper extends SQLiteOpenHelper {
    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "Semana.db";

    public SemanaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SemanaContract.EventoBD.CREATE_EVENTO);
        db.execSQL(SemanaContract.ParticipanteBD.CREATE_PARTICPANTE);
        db.execSQL(SemanaContract.EventoParticipanteBD.CREATE_EVENTO_PARTICIPANTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SemanaContract.EventoBD.DROP_EVENTO);
        db.execSQL(SemanaContract.EventoBD.DELETE_EVENTO);
        db.execSQL(SemanaContract.ParticipanteBD.DROP_PARTICPANTE);
        db.execSQL(SemanaContract.EventoParticipanteBD.DROP_EVENTO_PARTICIPANTE);
        onCreate(db);
    }


}