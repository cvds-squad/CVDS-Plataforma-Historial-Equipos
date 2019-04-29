/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.LaboratorioDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.LaboratorioMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.NovedadMapper;
import edu.eci.cvds.samples.entities.Laboratorio;
import edu.eci.cvds.samples.entities.Novedad;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 *
 * @author jcortes
 */
public class MyBatisLaboratorioDAO implements LaboratorioDAO{
    
    @Inject
    private LaboratorioMapper laboratorioMapper;

    @Override
    public void registrarLaboratorio(Laboratorio laboratorio) {
        try{
           laboratorioMapper.registrarLaboratorio(laboratorio);
       }
       catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("El laboratorio ya se encuetra registrado",e);   
       }
    }

    @Override
    public Laboratorio consultarLaboratorio(int idLab) {
        try{
            return laboratorioMapper.consultarLaboratorio(idLab);
        }catch (PersistenceException ex){
            throw new PersistenceException("Error al consultar el laboratorio",ex);
        }
    }

    @Override
    public int getMaxIdLaboratorio() {
        try{
            return laboratorioMapper.getMaxIdLaboratorio();
        }catch (PersistenceException ex){
            throw new PersistenceException("Error al consultar la maxima id del laboratorio",ex);
        }
    }

    @Override
    public void asociarEquipoConLaboratorio(int maxLabId, int idEquipo) {
        try{
            laboratorioMapper.asociarEquipoConLaboratorio(maxLabId,idEquipo);
        }catch (PersistenceException ex){
            throw new PersistenceException("Error al asociar equipo con laboratorio",ex);
        }
    }

    @Override
    public List<Laboratorio> consultarLaboratorios() {
        try{
            return laboratorioMapper.consultarLaboratorios();
        }catch (PersistenceException ex){
            throw new PersistenceException("Error al consultar los laboratorios");
        }
    }

}
