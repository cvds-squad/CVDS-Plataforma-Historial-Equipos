package edu.eci.cvds.samples.services.impl;

import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import java.util.HashMap;
import java.util.Map;

public class HistoryServiceStub implements HistoryService {
    
    private final Map<Integer,Equipo> equipos;
    
    public HistoryServiceStub(){
        equipos = new HashMap<>();
    }
    @Override
    public void registarEquipo(Equipo equipo) throws HistoryServiceException {
        if (!equipos.containsKey(equipo.getIdEquipo())) {
            equipos.put(equipo.getIdEquipo(), equipo);
	} else {
            throw new HistoryServiceException("El equipo ya se encuentra registrado.");
	}
    }

    //stub

}
