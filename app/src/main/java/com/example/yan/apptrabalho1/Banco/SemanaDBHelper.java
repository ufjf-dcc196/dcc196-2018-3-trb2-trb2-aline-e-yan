package com.example.yan.apptrabalho1.Banco;

import android.provider.BaseColumns;

public final class SemanaDBHelper {
    public final class EventoBD implements BaseColumns {
        public final static String TABLE_NAME = "Evento";
        public final static String COLUMN_NAME_TITULO = "Titulo";
        public final static String COLUMN_NAME_DIA = "DIA";
        public final static String COLUMN_NAME_HORA = "HORA";
        public final static String COLUMN_NAME_FACILITADOR = "FACILITADOR";
        public final static String COLUMN_NAME_DESCRICAO = "DESCRICAO";

        public final static String CREATE_EVENTO = "CREATE TABLE "+EventoBD.TABLE_NAME+" ("
                + EventoBD._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EventoBD.COLUMN_NAME_TITULO+ " TEXT "
                + EventoBD.COLUMN_NAME_DIA+ " TEXT "
                + EventoBD.COLUMN_NAME_HORA+ " TEXT "
                + EventoBD.COLUMN_NAME_FACILITADOR+ " TEXT "
                + EventoBD.COLUMN_NAME_DESCRICAO+ " TEXT "
                +")";
        public final static String DROP_EVENTO= "DROP TABLE IF EXISTS "+EventoBD.TABLE_NAME;

    }

    public final class ParticipanteBD implements BaseColumns {
        public final static String TABLE_NAME = "Seriado";
        public final static String COLUMN_NAME_NOME = "nome";
        public final static String COLUMN_NAME_EMAIL = "EMAIL";
        public final static String COLUMN_NAME_CPF = "CPF";
        public final static String COLUMN_NAME_MATRICULA = "MATRICULA";

        public final static String CREATE_PARTICPANTE  = "CREATE TABLE "+ParticipanteBD.TABLE_NAME+" ("
                + ParticipanteBD._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + ParticipanteBD.COLUMN_NAME_NOME+ " TEXT "
                + ParticipanteBD.COLUMN_NAME_EMAIL+ " TEXT "
                + ParticipanteBD.COLUMN_NAME_CPF+ " TEXT "
                + ParticipanteBD.COLUMN_NAME_MATRICULA+ " TEXT "
                +")";
        public final static String DROP_PARTICPANTE = "DROP TABLE IF EXISTS "+ParticipanteBD.TABLE_NAME;

    }
    public final class EventoParticipanteBD implements BaseColumns {
        public final static String TABLE_NAME = "Seriado";
        public final static String COLUMN_NAME_ID_EVENTO = "ID_EVENTO";
        public final static String COLUMN_NAME_ID_PARTICIPANTE = "ID_PARTICIPANTE";

        public final static String CREATE_EVENTO_PARTICIPANTE  = "CREATE TABLE "+EventoParticipanteBD.TABLE_NAME+" ("
                + EventoParticipanteBD._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EventoParticipanteBD.COLUMN_NAME_ID_EVENTO+ " INTEGER "
                + EventoParticipanteBD.COLUMN_NAME_ID_PARTICIPANTE+ " INTEGER "
                +")";
        public final static String DROP_EVENTO_PARTICIPANTE = "DROP TABLE IF EXISTS "+EventoParticipanteBD.TABLE_NAME;

    }
}
