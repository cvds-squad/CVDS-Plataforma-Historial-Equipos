/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Elemento;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author 2131381
 */
public interface ElementoMapper {
    void registerElemento(@Param("elem") Elemento elem);

    void registerElementoConId(@Param("elem") Elemento elem);

    Elemento consultElemento(@Param("eid") int id);

    List<Elemento> consultarElementosDisponibles();

    int getMaxId();

    List<Elemento> consultarElementos();

    List<Elemento> consultarTorresDisponibles();

    List<Elemento> consultarPantallasDisponibles();

    List<Elemento> consultarTecladosDisponibles();

    List<Elemento> consultarMousesDisponibles();
    
    void setDarDeBaja(@Param("eid") int id);

    List<Elemento> consultarElementosNoAsociados();

    List<Elemento> consultarElementosDadosDeBaja();
}
