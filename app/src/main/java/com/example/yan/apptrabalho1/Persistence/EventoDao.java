package com.example.yan.apptrabalho1.Persistence;

import com.example.yan.apptrabalho1.Modelo.Evento;
import com.example.yan.apptrabalho1.Modelo.Participante;

import java.util.ArrayList;

public class EventoDao {
    private static EventoDao instance = new EventoDao();
    private ArrayList<Evento> eventos = new ArrayList<>();

    private EventoDao() {
        Evento e1 = new Evento();
        Evento e2 = new Evento();
        Evento e3 = new Evento();

        e1.setDescricao("Este evento tem como objetivo aprendizado de android").
                setDia("04/11/2018").setFacilitador("Aline de Paula").
                setHora("22:00 horas").setTitulo("Mini curso de Android");
        e2.setDescricao("Este evento tem como objetivo aprendizado de LaTeX").
                setDia("05/11/2018").setFacilitador("José das Couves").
                setHora("22:00 horas").setTitulo("Mini curso de LaTeX");
        e3.setDescricao("Este evento tem como objetivo aprendizado de Gamão").
                setDia("06/11/2018").setFacilitador("Lucas Rei do Gamão").
                setHora("22:00 horas").setTitulo("Mini curso de Gamão");
        eventos.add(e1);
        eventos.add(e2);
        eventos.add(e3);
    }
    public static EventoDao getInstance(){
        return instance;
    }

    public ArrayList<Evento> getEventos() {
        return eventos;
    }
    public void addEvento(Evento e){
        eventos.add(e);
    }
    public void removeEvento(Evento e){
        eventos.remove(e);
    }
    public int getIndiceEvento(Evento e){
        for(int i =0; i< eventos.size(); i++){
            if(eventos.get(i).equals(e)){
                return i;
            }
        }
        return -1;
    }
    public void removeParticipanteEvento(Participante p){
        for (Evento e: EventoDao.getInstance().getEventos()) {
            if(e.getParticipantes().contains(p)){
                int i = EventoDao.getInstance().getEventos().indexOf(e);
                EventoDao.getInstance().getEventos().get(i).removeParticipante(p);
            }
        }
    }
}
