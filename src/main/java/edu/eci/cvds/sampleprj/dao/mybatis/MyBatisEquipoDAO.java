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
    /**
     * Asocia un elemento a un equipo
     * @param equipoID El id del equipo a asociar un elemento
     * @param elementoID El id del elemento a asociar
     **/
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

}
