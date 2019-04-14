package edu.eci.cvds.samples.services;


import edu.eci.cvds.samples.entities.*;


public interface HistoryService {
    
    void registarEquipo(Equipo equipo) throws HistoryServiceException;

    void registrarElemento(Elemento elem) throws HistoryServiceException;

    Elemento consultarElemento(int id) throws HistoryServiceException;

}
