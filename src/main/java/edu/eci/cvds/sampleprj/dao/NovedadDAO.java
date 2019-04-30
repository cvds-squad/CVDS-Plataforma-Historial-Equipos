/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Novedad;
import javax.persistence.PersistenceException;
import java.util.List;


/**
 *
 * @author jcortes
 */
public interface NovedadDAO {
    
    void registrarNovedad(Novedad novedad) throws PersistenceException;
    
    Novedad consultarNovedadId (int idNovedad) throws PersistenceException;
    
    Novedad consultarNovedadEquipo (int idEquipo) throws PersistenceException;
    
    Novedad consultarNovedadElementoEquipo (int idElemento, int idEquipo) throws PersistenceException;

    List<Novedad> consultarNovedades() throws PersistenceException;
}
