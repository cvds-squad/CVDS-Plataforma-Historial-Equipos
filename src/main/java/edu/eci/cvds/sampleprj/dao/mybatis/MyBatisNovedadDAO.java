/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.NovedadDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.NovedadMapper;
import edu.eci.cvds.samples.entities.Novedad;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 *
 * @author jcortes
 */
public class MyBatisNovedadDAO implements NovedadDAO {
          
    @Inject
    private NovedadMapper novedadMapper;

    @Override
    public void registrarNovedad(Novedad novedad) throws PersistenceException {
       try{
           novedadMapper.registrarNovedad(novedad);
       }
       catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("La novedad ya se encuetra registrada",e);   
       }
    }

    @Override
    public Novedad consultarNovedadId(int idNovedad) throws PersistenceException {
        try{
            return novedadMapper.consultarNovedadId(idNovedad);
        }catch (PersistenceException ex){
            throw new PersistenceException("Error al consultar novedad",ex);
        }
    }

    @Override
    public Novedad consultarNovedadEquipo(int idEquipo) throws PersistenceException {
        try{
            return novedadMapper.consultarNovedadEquipo(idEquipo);
        }catch (PersistenceException ex){
            throw new PersistenceException("Error al consultar novedad",ex);
        }
    }

    @Override
    public Novedad consultarNovedadElementoEquipo(int idElemento, int idEquipo) throws PersistenceException {
        try{
            return novedadMapper.consultarNovedadElementoEquipo(idElemento, idEquipo);
        }catch (PersistenceException ex){
            throw new PersistenceException("Error al consultar novedad",ex);
        }
    }

    @Override
    public List<Novedad> consultarNovedades() throws PersistenceException {
        try{
            return novedadMapper.consultarNovedades();
        }catch (PersistenceException ex){
            throw new PersistenceException("Error al consultar novedades",ex);
        }
    }
}
