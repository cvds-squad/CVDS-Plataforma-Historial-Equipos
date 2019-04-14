package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.samples.entities.Equipo;

import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import javax.persistence.PersistenceException;

public class HistoryServiceImpl implements HistoryService {

    @Inject
    private ElementoDAO elementoDAO;
    @Inject
    private EquipoDAO equipoDAO;

    @Override
    public void registarEquipo(Equipo equipo) throws HistoryServiceException {
        try{
            equipoDAO.registrarEquipo(equipo);
        } catch (PersistenceException e) {
            throw new HistoryServiceException("Error al registar el equipo.",e);
        }
    }


    @Override
    public void registrarElemento(Elemento elem) throws HistoryServiceException {
        try{
            elementoDAO.registerElemento(elem);
        }
        catch(PersistenceException ex){
            throw new HistoryServiceException("No fue posible registrar el elemento",ex);  
        }
    }

    @Override
    public Elemento consultarElemento(int id) throws HistoryServiceException {
        return elementoDAO.consultarElemento(id);
    }
}
