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
import javax.persistence.PersistenceException;

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
    
}
