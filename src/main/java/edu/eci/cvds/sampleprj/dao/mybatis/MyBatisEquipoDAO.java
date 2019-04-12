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
    
}
