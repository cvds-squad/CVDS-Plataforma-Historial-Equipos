package edu.eci.cvds.samples.services;

import edu.eci.cvds.samples.entities.Equipo;

public interface HistoryService {
    
    public abstract void registarEquipo(Equipo equipo) throws HistoryServiceException;

    

}
