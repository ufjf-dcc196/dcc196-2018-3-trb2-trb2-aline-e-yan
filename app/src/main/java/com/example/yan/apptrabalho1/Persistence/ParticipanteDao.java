package com.example.yan.apptrabalho1.Persistence;

import com.example.yan.apptrabalho1.Modelo.Evento;
import com.example.yan.apptrabalho1.Modelo.Participante;

import java.util.ArrayList;

public class ParticipanteDao {
    private static ParticipanteDao instance = new ParticipanteDao();
    private ArrayList<Participante> participantes = new ArrayList<>();

    private ParticipanteDao() {
//        Participante p1 = new Participante();
//        Participante p2 = new Participante();
//        Participante p3 = new Participante();
//
//        p1.setNome("Aline de Paula").setCpf("123.321.159-05").setEmail("alunePaula@gmail.com").setMatricula("201476055").
//                addEvento(EventoDao.getInstance().getEventos().get(0));
//        p2.setNome("Yan de Paiva").setCpf("555.777.888-66").
//                setEmail("YanPaiva@gmail.com").setMatricula("201476038").
//                addEvento(EventoDao.getInstance().getEventos().get(0));
//        p3.setNome("Fernanda Nunes").setCpf("111.222.333-44").
//                setEmail("fefe@gmail.com").setMatricula("201476001").
//                addEvento(EventoDao.getInstance().getEventos().get(0));
//
//        participantes.add(p1);
//        participantes.add(p2);
//        participantes.add(p3);
//        EventoDao.getInstance().getEventos().get(0).addParticipante(participantes.get(0));
//        EventoDao.getInstance().getEventos().get(0).addParticipante(participantes.get(1));
//        EventoDao.getInstance().getEventos().get(0).addParticipante(participantes.get(2));
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
    public void removeParticipante(int indice){
        participantes.remove(indice);
    }
    public void removeAllParticipanteEvento(Evento e){
        for (Participante p: ParticipanteDao.getInstance().getParticipantes()) {
            if(p.getMeusEventos().contains(e)){
                int i = ParticipanteDao.getInstance().getParticipantes().indexOf(p);
                ParticipanteDao.getInstance().getParticipantes().get(i).removeEvento(e);
            }
        }
    }
    public void removeParticipanteDoEvento(Evento e, Participante p){
        int indice = ParticipanteDao.getInstance().getParticipantes().indexOf(p);
        ParticipanteDao.getInstance().getParticipantes().get(indice).removeEvento(e);
    }
}
