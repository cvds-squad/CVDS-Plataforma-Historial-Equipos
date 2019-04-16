/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.EquipoMapper;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.services.HistoryService;

import javax.persistence.PersistenceException;
import java.util.List;

/**
 *
 * @author jcortes
 */
public class MyBatisEquipoDAO  implements EquipoDAO{
    
    @Inject
    private EquipoMapper equipoMapper;

    @Override
    public void registrarEquipo(Equipo equipo) throws PersistenceException {
       try{
           equipoMapper.registrarEquipo(equipo);
       }
       catch(org.apache.ibatis.exceptions.PersistenceException e){
             throw new PersistenceException("El equipo ya se encuetra registrado",e);   
       }
    }

    @Override
    public void asociarElemento(int equipoId, int elementoId) throws PersistenceException {
        try{
            equipoMapper.eliminarAsociacion(equipoId,elementoId); //eliminar anterior link de elemento
            equipoMapper.asociarElemento(equipoId,elementoId);
        }catch(PersistenceException ex){
            throw new PersistenceException("Error al asociar el elemento",ex);
        }
    }

    @Override
    public List<Equipo> consultarEquipos() {
        return equipoMapper.consultarEquipos();
    }

    @Override
    public Equipo consultaEquipo(int idEquipo) {
        try{
            return equipoMapper.consultarEquipo(idEquipo);
        }catch (PersistenceException ex){
            throw new PersistenceException("Error al consultar equipo",ex);
        }
    }

    @Override
    public int getIdMax() throws PersistenceException {
        try{
            return equipoMapper.getMaxId();
        }catch (PersistenceException ex){
            throw new PersistenceException("Error al consultar la maxima id",ex);
        }
    }

}
