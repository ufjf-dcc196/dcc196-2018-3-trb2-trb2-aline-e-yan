package com.example.yan.apptrabalho1.Modelo;

import java.util.ArrayList;

public class Evento {

    private String titulo, dia, hora, facilitador,descricao;;
    private int id;

    public Evento() {
    }

    public Evento(String titulo, String dia, String hora, String facilitador, String descricao) {
        this.titulo = titulo;
        this.dia = dia;
        this.hora = hora;
        this.facilitador = facilitador;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public Evento setId(int id) {
        this.id = id;
        return this;
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

/*    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void addParticipante(Participante p){
        this.participantes.add(p);
    }
    public void removeParticipante(Participante p){
        this.participantes.remove(p);
    }
    public void removeParticipante(int indice){
        this.participantes.remove(indice);
    }
    public void removeAllParticipantes(){
        participantes.removeAll(participantes);
    }

*/
}
