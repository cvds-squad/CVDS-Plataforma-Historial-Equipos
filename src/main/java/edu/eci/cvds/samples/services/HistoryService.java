package edu.eci.cvds.samples.services;


import edu.eci.cvds.samples.entities.*;

import java.util.List;


public interface HistoryService {
    
    void registarEquipo(Equipo equipo) throws HistoryServiceException;

    void registrarElemento(Elemento elem) throws HistoryServiceException;
    void registrarElementoConId(Elemento elem) throws HistoryServiceException;

    Elemento consultarElemento(int id) throws HistoryServiceException;

    void asociarElementoConEquipo(int equipoId,int elementoId) throws HistoryServiceException;

    List<Equipo> consultarEquipos() throws HistoryServiceException;

    List<Elemento> consultarElementosDisponibles() throws HistoryServiceException;
}
