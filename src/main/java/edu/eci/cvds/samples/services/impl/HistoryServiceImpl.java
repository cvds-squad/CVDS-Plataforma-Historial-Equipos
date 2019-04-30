package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.LaboratorioDAO;
import edu.eci.cvds.sampleprj.dao.NovedadDAO;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import edu.eci.cvds.samples.entities.Novedad;

import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import javax.persistence.PersistenceException;
import java.util.List;

public class HistoryServiceImpl implements HistoryService {

    @Inject
    private ElementoDAO elementoDAO;
    @Inject
    private EquipoDAO equipoDAO;
    @Inject
    private NovedadDAO novedadDAO;
    @Inject
    private LaboratorioDAO laboratorioDAO;
    
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

    @Override
    /**
     * Obtiene todos los elementos registrados en la base de datos
     * @return Lista de elementos
     **/
    public List<Elemento> consultarElementos() throws HistoryServiceException {
        try{
            return elementoDAO.consultarElementos();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar los elementos registrados");
        }
    }

    @Override
    public List<Elemento> consultarTorresDisponibles() throws HistoryServiceException {
        try{
            return elementoDAO.consultarTorresDisponibles();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar las torres disponibles");
        }
    }

    @Override
    public List<Elemento> consultarPantallasDisponibles() throws HistoryServiceException {
        try{
            return elementoDAO.consultarPantallasDisponibles();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar las torres disponibles");
        }
    }

    @Override
    public List<Elemento> consultarTecladosDisponibles() throws HistoryServiceException {
        try{
            return elementoDAO.consultarTecladosDisponibles();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar las torres disponibles");
        }
    }

    @Override
    public List<Elemento> consultarMousesDisponibles() throws HistoryServiceException {
        try{
            return elementoDAO.consultarMousesDisponibles();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar las torres disponibles");
        }
    }

    @Override
    public void registrarNovedad(Novedad novedad) throws HistoryServiceException {
        try{
            novedadDAO.registrarNovedad(novedad);
        }catch (PersistenceException ex){
            throw new HistoryServiceException("Error al registrar novedad");
        }
    }

    @Override
    public Novedad consultarNovedadDadoId(int idNovedad) throws HistoryServiceException {
        try{
            return novedadDAO.consultarNovedadId(idNovedad);
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar la novedad");
        }
    }

    @Override
    public Novedad consultarNovedadDadoEquipo(int idEquipo) throws HistoryServiceException {
        try{
            return novedadDAO.consultarNovedadEquipo(idEquipo);
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar la novedad");
        }
    }

    @Override
    public Novedad consultarNovedadDadoElementoEquipo(int idElemento, int idEquipo) throws HistoryServiceException {
        try{
            return novedadDAO.consultarNovedadElementoEquipo(idElemento, idEquipo);
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar la novedad");
        }
    }

    @Override
    public void registrarLaboratorio(Laboratorio laboratorio) throws HistoryServiceException {
        try{
            laboratorioDAO.registrarLaboratorio(laboratorio);
        }catch (PersistenceException ex){
            throw new HistoryServiceException("Error al registar novedad");
        }
    }

    @Override
    public Laboratorio consultarLaboratorio(int idLab) throws HistoryServiceException {
        try{
            return laboratorioDAO.consultarLaboratorio(idLab);
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar el laboratorio");
        }
    }

    @Override
    public int getIdMaxLaboratorio() throws HistoryServiceException {
        try{
            return laboratorioDAO.getMaxIdLaboratorio();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar la id maxima de laboratorio");
        }
    }

    @Override
    public void asociarEquipoConLaboratorio(int maxLabId, int idEquipo) throws HistoryServiceException {
        try{
            laboratorioDAO.asociarEquipoConLaboratorio(maxLabId,idEquipo);
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo asociar el equipo con el laboratorio");
        }
    }

    @Override
    public List<Laboratorio> consultarLaboratorios() throws HistoryServiceException {
        try{
            return laboratorioDAO.consultarLaboratorios();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar los laboratorios");
        }
    }

    @Override
    public Integer consultarLabAsociadoAEquipo(int idEquipo) throws HistoryServiceException {
        try{
            return equipoDAO.consultarLabAsociado(idEquipo);
        }catch (PersistenceException ex){
            throw  new HistoryServiceException("No se pudo consultar el laboratorio asociado");
        }
    }

    @Override
    public void darBajaElemento(int id) throws HistoryServiceException {
        try{
            elementoDAO.darDeBajaElem(id);
        }catch(PersistenceException ex){
            throw new HistoryServiceException("El elemento ya está de baja."); 
        }
    }

    @Override
    public void darBajaEquipo(int idEquipo) throws HistoryServiceException {
        try{
            equipoDAO.darDeBajaEqp(idEquipo);
        }catch (PersistenceException ex){
            throw new HistoryServiceException("El equipo ya está de baja.");
        }
    }

    @Override
    public List<Elemento> consultarElementosNoAsociados() throws HistoryServiceException {
        try{
            return elementoDAO.consultarElementosNoAsociados();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar los elementos no asociados");
        }
    }

    @Override
    public List<Elemento> consultarElementosDadosDeBaja() throws HistoryServiceException {
        try{
            return elementoDAO.consultarElementosDadosDeBaja();
        }catch (PersistenceException ex){
            throw new HistoryServiceException("No se pudo consultar elementos dado de baja");
        }
    }

    @Override
    public void quitarAsociacionConEquipo(int idElemento) throws HistoryServiceException {
        try{
            elementoDAO.quitarAsociacionConEquipo(idElemento);
        }catch (PersistenceException ex){
            throw  new HistoryServiceException("No se pudo desasociar al elemento del equipo");
        }
    }

    @Override
    public List<Equipo> consultarEquiposNoDeBaja() throws HistoryServiceException {
        try{
            return equipoDAO.consultarEquiposNoDeBaja();
        }catch (PersistenceException ex){
            throw  new HistoryServiceException("No se pudo consultar los equipos");
        }
    }
}
