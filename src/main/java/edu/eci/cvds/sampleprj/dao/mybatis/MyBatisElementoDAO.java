/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ElementoMapper;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Novedad;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 *
 * @author 2131381
 */
public class MyBatisElementoDAO  implements ElementoDAO{
    
    @Inject
    private ElementoMapper elementoMapper;

    @Override
    public void registerElemento(Elemento element) throws PersistenceException {
       try{
           elementoMapper.registerElemento(element);
       }
       catch(org.apache.ibatis.exceptions.PersistenceException e){
           throw new PersistenceException("Elemento ya existe",e);
       }
    }

    @Override
    public void registerElementoConId(Elemento element) throws PersistenceException {
        try{
            elementoMapper.registerElementoConId(element);
        }catch (PersistenceException e){
            throw new PersistenceException("Elemento ya existe",e);
        }
    }

    @Override
    public Elemento consultarElemento(int id){
        return elementoMapper.consultElemento(id);
    }

    @Override
    public List<Elemento> consultarElementosDisponibles() {
        try{
            return elementoMapper.consultarElementosDisponibles();
        }catch (PersistenceException ex){
            throw new PersistenceException("No se puede consultar",ex);
        }
    }

    @Override
    public int getIdMax() throws PersistenceException {
        try{
            return elementoMapper.getMaxId();
        }catch (PersistenceException ex){
            throw new PersistenceException("No se pudo consultar la maxima id",ex);
        }
    }

    @Override
    public List<Elemento> consultarElementos() throws PersistenceException{
        try{
            return elementoMapper.consultarElementos();
        }catch (PersistenceException ex){
            throw new PersistenceException("No se pudo consultar los elementos",ex);
        }
    }

    @Override
    public List<Elemento> consultarTorresDisponibles() {
        try{
            return elementoMapper.consultarTorresDisponibles();
        }catch (PersistenceException ex){
            throw new PersistenceException("No se pudo consultar las torres disponibles",ex);
        }
    }

    @Override
    public List<Elemento> consultarPantallasDisponibles() {
        try{
            return elementoMapper.consultarPantallasDisponibles();
        }catch (PersistenceException ex){
            throw new PersistenceException("No se pudo consultar las torres disponibles",ex);
        }
    }

    @Override
    public List<Elemento> consultarTecladosDisponibles() {
        try{
            return elementoMapper.consultarTecladosDisponibles();
        }catch (PersistenceException ex){
            throw new PersistenceException("No se pudo consultar las torres disponibles",ex);
        }
    }

    @Override
    public List<Elemento> consultarMousesDisponibles() {
        try{
            return elementoMapper.consultarMousesDisponibles();
        }catch (PersistenceException ex){
            throw new PersistenceException("No se pudo consultar las torres disponibles",ex);
        }
    }

    @Override
    public void darDeBajaElem(int id) throws PersistenceException {
        try{
            elementoMapper.setDarDeBaja(id);
        }catch (PersistenceException ex){
            throw new PersistenceException("El elemento ya está de baja", ex);
        }
    }

    @Override
    public List<Elemento> consultarElementosNoAsociados() {
        try{
            return elementoMapper.consultarElementosNoAsociados();
        }catch (PersistenceException ex){
            throw new PersistenceException("No se pudo consultar elementos no asociados", ex);
        }
    }

    @Override
    public List<Elemento> consultarElementosDadosDeBaja() {
        try{
            return elementoMapper.consultarElementosDadosDeBaja();
        }catch (PersistenceException ex){
            throw new PersistenceException("No se pudo consultar elementos dados de baja");
        }
    }

    @Override
    public void quitarAsociacionConEquipo(int idElemento) {
        try{
            elementoMapper.quitarAsociacionConEquipo(idElemento);
        }catch (PersistenceException ex){
            throw new PersistenceException("No se pudo desasociar el elemento del equipo");
        }
    }

    @Override
    public List<Novedad> consultarNovedades(int idElemento) {
        try{
            return elementoMapper.consultarNovedadesDeElemento(idElemento);
        }catch (PersistenceException ex){
            throw new PersistenceException("No se pudo consultar las novedades del elemento");
        }
    }
}
