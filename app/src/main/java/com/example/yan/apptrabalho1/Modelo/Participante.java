package com.example.yan.apptrabalho1.Modelo;

import java.util.ArrayList;

public class Participante {

    private String nome, email, cpf, matricula;
//    private ArrayList<Evento> meusEventos = new ArrayList<>();
    private int id;

    public Participante() {
    }

    public Participante(String nome, String email, String cpf, String matricula) {
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.matricula = matricula;
    }

    public int getId() {
        return id;
    }

    public Participante setId(int id) {
        this.id = id;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Participante setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Participante setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Participante setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public String getMatricula() {
        return matricula;
    }

    public Participante setMatricula(String matricula) {
        this.matricula = matricula;
        return this;
    }
/*
    public ArrayList<Evento> getMeusEventos() {
        return meusEventos;
    }

    public Participante setMeusEventos(ArrayList<Evento> meusEventos) {
        this.meusEventos = meusEventos;
        return this;
    }
    public void addEvento(Evento e){
        meusEventos.add(e);
    }
    public void removeEvento(Evento e){
        meusEventos.remove(e);
    }
    public void removeEvento(int indice){
        meusEventos.remove(indice);
    }*/
}
