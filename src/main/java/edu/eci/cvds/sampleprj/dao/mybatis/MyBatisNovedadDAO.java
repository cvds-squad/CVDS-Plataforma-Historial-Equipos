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
    
}
