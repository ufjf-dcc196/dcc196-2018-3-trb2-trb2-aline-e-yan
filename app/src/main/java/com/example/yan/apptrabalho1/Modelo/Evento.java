package com.example.yan.apptrabalho1.Modelo;

public class Evento {

    private String titulo, dia, hora, facilitador,descricao;;

    public Evento() {
    }

    public Evento(String titulo, String dia, String hora, String facilitador, String descricao) {
        this.titulo = titulo;
        this.dia = dia;
        this.hora = hora;
        this.facilitador = facilitador;
        this.descricao = descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public Evento setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getDia() {
        return dia;
    }

    public Evento setDia(String dia) {
        this.dia = dia;
        return this;
    }

    public String getHora() {
        return hora;
    }

    public Evento setHora(String hora) {
        this.hora = hora;
        return this;
    }

    public String getFacilitador() {
        return facilitador;
    }

    public Evento setFacilitador(String facilitador) {
        this.facilitador = facilitador;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Evento setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }
}
