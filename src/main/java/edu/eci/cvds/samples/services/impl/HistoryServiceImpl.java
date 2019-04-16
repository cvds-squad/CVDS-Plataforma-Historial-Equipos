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
    /**
     * Registra un equipo
     * @param equipo Equipo a registrar
     * */
    public void registarEquipo(Equipo equipo) throws HistoryServiceException {
        try{
            equipoDAO.registrarEquipo(equipo);
        } catch (PersistenceException e) {
            throw new HistoryServiceException("Error al registar el equipo.",e);
        }
    }


    @Override
    /**
     * Registra un elemento
     * @param elem Elemento a registrar
     * */
    public void registrarElemento(Elemento elem) throws HistoryServiceException {
        try{
            elementoDAO.registerElemento(elem);
        }
        catch(PersistenceException ex){
            throw new HistoryServiceException("No fue posible registrar el elemento",ex);  
        }
    }

    @Override
    /**
     * Registra un elemento al cual se le indico una Id
     * @param elem Elemento con id a registrar
     * */
    public void registrarElementoConId(Elemento elem) throws HistoryServiceException {
        try{
            elementoDAO.registerElementoConId(elem);
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No fue posible registrar el elemento", ex);
        }
    }

    @Override
    /**
     * Consulta un elemento dada una id
     * @param id La id que se utilizara en la busqueda
     * @return elemento consultado, null en caso de no encontrar
     * */
    public Elemento consultarElemento(int id) throws HistoryServiceException {
        return elementoDAO.consultarElemento(id);
    }

    @Override
    /**
     * Asocia un elemento con un equipo
     * @param equipoId La id del equipo a asociar el elemento
     * @param elementoId La id del elemento a asociar
     * */
    public void asociarElementoConEquipo(int equipoId, int elementoId) throws HistoryServiceException {
        try{
            equipoDAO.asociarElemento(equipoId,elementoId);
        }catch (PersistenceException  ex){
            throw new HistoryServiceException("No se pudo asociar el elemento al equipo",ex);
        }
    }

    @Override
    /**
     * Consulta todos los equipos
     * @return Lista de todos los equipos
     **/
    public List<Equipo> consultarEquipos() {
        return equipoDAO.consultarEquipos();
    }

    @Override
    /**
     * Consulta todos los elementos disponibles
     * @return Lista de todos los elementos disponibles
     **/
    public List<Elemento> consultarElementosDisponibles() throws HistoryServiceException{
        try{
            return elementoDAO.consultarElementosDisponibles();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar elementos disponibles.",ex);
        }
    }

    @Override
    /**
     * Consulta un equipo dada una id
     * @param id La id que se utiliza en la busqueda
     * @return El equipo, null en caso de no encontrar
     * */
    public Equipo consultaEquipo(int idEquipo) throws HistoryServiceException{
        try{
            return equipoDAO.consultaEquipo(idEquipo);
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar el equipo con id " + idEquipo, ex);
        }
    }

    @Override
    /**
     * Obtiene la id siguiente actual de los elementos registrados
     * @return id mas alta
     **/
    public int getIdMaxElemento() throws HistoryServiceException{
        try{
            return elementoDAO.getIdMax();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo encontrar la maxima id");
        }
    }

    @Override
    /**
     * Obtiene la id siguiente actual de los equipos registrados
     * @return id mas alta
     **/
    public int getIdMaxEquipo() throws HistoryServiceException {
        try{
            return equipoDAO.getIdMax();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo encontrar la maxima id");
        }
    }
}
