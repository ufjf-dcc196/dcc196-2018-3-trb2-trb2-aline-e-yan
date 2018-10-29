package com.example.yan.apptrabalho1.Persistence;

import com.example.yan.apptrabalho1.Modelo.Participante;

import java.util.ArrayList;

public class ParticipanteDao {
    private static ParticipanteDao instance = new ParticipanteDao();
    private ArrayList<Participante> participantes = new ArrayList<>();

    private ParticipanteDao() {
        Participante p1 = new Participante();
        Participante p2 = new Participante();
        Participante p3 = new Participante();

        p1.setNome("Aline de Paula").setCpf("123.321.159-05").setEmail("alunePaula@gmail.com").setMatricula("201476055");
        p2.setNome("Yan de Paiva").setCpf("555.777.888-66").setEmail("YanPaiva@gmail.com").setMatricula("201476038");
        p3.setNome("Fernanda Nunes").setCpf("111.222.333-44").setEmail("fefe@gmail.com").setMatricula("201476001");

        participantes.add(p1);
        participantes.add(p2);
        participantes.add(p3);
    }
    public static ParticipanteDao getInstance(){
        return  instance;
    }

    public ArrayList<Participante> getParticipantes() {
        return participantes;
    }

    public void addParticipante(Participante p){
        participantes.add(p);
    }
    public void removeParticipante(Participante p){
        participantes.remove(p);
    }
    public void removeParticipante(int indice){
        participantes.remove(indice);
    }
}
