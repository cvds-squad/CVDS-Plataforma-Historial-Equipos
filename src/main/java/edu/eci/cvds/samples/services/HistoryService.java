package edu.eci.cvds.samples.services;


import edu.eci.cvds.samples.entities.*;


public interface HistoryService {
    
    public abstract void registarEquipo(Equipo equipo) throws HistoryServiceException;

    public abstract void registrarElemento(Elemento elem) throws HistoryServiceException;

    public abstract Elemento consultarElemento(int id) throws HistoryServiceException;

}
