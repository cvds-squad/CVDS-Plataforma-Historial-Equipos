package edu.eci.cvds.samples.services;


import edu.eci.cvds.samples.entities.*;

import java.util.List;
import javax.persistence.PersistenceException;


public interface HistoryService {

    void registarEquipo(Equipo equipo) throws HistoryServiceException;

    void registrarElemento(Elemento elem) throws HistoryServiceException;

    void registrarElementoConId(Elemento elem) throws HistoryServiceException;

    Elemento consultarElemento(int id) throws HistoryServiceException;

    void asociarElementoConEquipo(int equipoId, int elementoId) throws HistoryServiceException;

    List<Equipo> consultarEquipos() throws HistoryServiceException;

    List<Elemento> consultarElementosDisponibles() throws HistoryServiceException;

    Equipo consultaEquipo(int idEquipo) throws HistoryServiceException;

    int getIdMaxElemento() throws HistoryServiceException;

    int getIdMaxEquipo() throws HistoryServiceException;

    List<Elemento> consultarElementos() throws HistoryServiceException;

    List<Elemento> consultarTorresDisponibles() throws HistoryServiceException;

    List<Elemento> consultarPantallasDisponibles() throws HistoryServiceException;

    List<Elemento> consultarTecladosDisponibles() throws HistoryServiceException;

    List<Elemento> consultarMousesDisponibles() throws HistoryServiceException;

    public void registrarNovedad(Novedad novedad) throws HistoryServiceException;

    public Novedad consultarNovedadDadoId(int idNovedad) throws HistoryServiceException;

    public Novedad consultarNovedadDadoEquipo(int idEquipo) throws HistoryServiceException;

    public Novedad consultarNovedadDadoElementoEquipo(int idElemento, int idEquipo) throws HistoryServiceException;

    public void registrarLaboratorio(Laboratorio laboratorio) throws HistoryServiceException;

    public Laboratorio consultarLaboratorio(int idLab) throws HistoryServiceException;

    int getIdMaxLaboratorio() throws HistoryServiceException;

    void asociarEquipoConLaboratorio(int maxLabId, int idEquipo) throws HistoryServiceException;

    List<Laboratorio> consultarLaboratorios() throws HistoryServiceException;

    Integer consultarLabAsociadoAEquipo(int idEquipo) throws HistoryServiceException;

    void darBajaElemento(int id) throws HistoryServiceException;

    void darBajaEquipo(int idEquipo) throws HistoryServiceException;

    List<Elemento> consultarElementosNoAsociados() throws HistoryServiceException;

    List<Elemento> consultarElementosDadosDeBaja() throws HistoryServiceException;

    void quitarAsociacionConEquipo(int idElemento) throws HistoryServiceException;

    List<Equipo> consultarEquiposNoDeBaja() throws HistoryServiceException;

    List<Novedad> consultarNovedades() throws HistoryServiceException;

    List<Equipo> consultarEquiposDeLaboratorio(int idLab) throws HistoryServiceException;
}
