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

    Equipo consultaEquipo(int idEquipo) throws HistoryServiceException;

    int getIdMaxElemento() throws HistoryServiceException;

    int getIdMaxEquipo() throws HistoryServiceException;

    List<Elemento> consultarElementos() throws  HistoryServiceException;

    List<Elemento> consultarTorresDisponibles() throws HistoryServiceException;

    List<Elemento> consultarPantallasDisponibles() throws HistoryServiceException;

    List<Elemento> consultarTecladosDisponibles() throws HistoryServiceException;

    List<Elemento> consultarMousesDisponibles() throws HistoryServiceException;
}
