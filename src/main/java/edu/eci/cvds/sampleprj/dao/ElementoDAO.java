/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Elemento;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 *
 * @author 2131381
 */
public interface ElementoDAO {
    
    public void registerElemento(Elemento element) throws PersistenceException;

    void registerElementoConId(Elemento element) throws PersistenceException;

    public Elemento consultarElemento(int id) throws PersistenceException;

    List<Elemento> consultarElementosDisponibles();
}
