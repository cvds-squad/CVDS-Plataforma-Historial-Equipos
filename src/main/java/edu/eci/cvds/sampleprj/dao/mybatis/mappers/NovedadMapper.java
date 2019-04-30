/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Novedad;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author jcortes
 */
public interface NovedadMapper {
    
    void registrarNovedad(@Param("novedad") Novedad novedad);
    
    Novedad consultarNovedadId (@Param("idNovedad") int idNovedad);
    
    Novedad consultarNovedadEquipo (@Param("idEquipo") int idEquipo);
    
    Novedad consultarNovedadElementoEquipo (@Param("idElemento") int idElemento, @Param("idEquipo") int idEquipo );

    List<Novedad> consultarNovedades();
}
