package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.samples.entities.Equipo;

import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import javax.persistence.PersistenceException;
import java.util.List;

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
    public void registrarElementoConId(Elemento elem) throws HistoryServiceException {
        try{
            elementoDAO.registerElementoConId(elem);
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No fue posible registrar el elemento", ex);
        }
    }

    @Override
    public Elemento consultarElemento(int id) throws HistoryServiceException {
        return elementoDAO.consultarElemento(id);
    }

    @Override
    public void asociarElementoConEquipo(int equipoId, int elementoId) throws HistoryServiceException {
        try{
            equipoDAO.asociarElemento(equipoId,elementoId);
        }catch (PersistenceException  ex){
            throw new HistoryServiceException("No se pudo asociar el elemento al equipo",ex);
        }
    }

    @Override
    public List<Equipo> consultarEquipos() {
        return equipoDAO.consultarEquipos();
    }

    @Override
    public List<Elemento> consultarElementosDisponibles() throws HistoryServiceException{
        try{
            return elementoDAO.consultarElementosDisponibles();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar elementos disponibles.",ex);
        }
    }

    @Override
    public Equipo consultaEquipo(int idEquipo) throws HistoryServiceException{
        try{
            return equipoDAO.consultaEquipo(idEquipo);
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar el equipo con id " + idEquipo, ex);
        }
    }

    @Override
    public int getIdMaxElemento() throws HistoryServiceException{
        try{
            return elementoDAO.getIdMax();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo encontrar la maxima id");
        }
    }

    @Override
    public int getIdMaxEquipo() throws HistoryServiceException {
        try{
            return equipoDAO.getIdMax();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo encontrar la maxima id");
        }
    }
}
