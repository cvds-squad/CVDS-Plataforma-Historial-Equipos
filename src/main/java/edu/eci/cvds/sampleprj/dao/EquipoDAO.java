/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import javax.persistence.PersistenceException;
import java.util.List;

/**
 *
 * @author jcortes
 */
public interface EquipoDAO {
    
    public void registrarEquipo(Equipo equipo) throws PersistenceException;

    void asociarElemento(int equipoId, int elementoId) throws PersistenceException;

    List<Equipo> consultarEquipos();

    Equipo consultaEquipo(int idEquipo);

    int getIdMax() throws PersistenceException;

    Integer consultarLabAsociado(int idEquipo);
    
    void darDeBajaEqp(int idEquipo) throws PersistenceException;

    List<Equipo> consultarEquiposNoDeBaja();
}
