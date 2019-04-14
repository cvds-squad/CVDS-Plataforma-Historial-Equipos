/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Equipo;
import javax.persistence.PersistenceException;

/**
 *
 * @author jcortes
 */
public interface EquipoDAO {
    
    public void registrarEquipo(Equipo equipo) throws PersistenceException;

    void asociarElemento(int equipoId, int elementoId) throws PersistenceException;
}
