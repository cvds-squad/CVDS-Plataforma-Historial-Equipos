/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Novedad;
import javax.persistence.PersistenceException;

/**
 *
 * @author jcortes
 */
public interface NovedadDAO {
    
    public void registrarNovedad(Novedad novedad) throws PersistenceException;
}
