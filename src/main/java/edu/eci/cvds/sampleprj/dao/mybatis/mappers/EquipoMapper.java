/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Novedad;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @author jcortes
 */
public interface EquipoMapper {
    public void registrarEquipo(@Param("equipo") Equipo equipo);

    void eliminarAsociacion(@Param("idEqui") int equipoId,@Param("idElem") int elemId);

    void asociarElemento(@Param("idEqui") int equipoId,@Param("idElem") int elementoId);

    List<Equipo> consultarEquipos();

    Equipo consultarEquipo(@Param("idEqui") int idEquipo);

    int getMaxId();

    Integer consultarLabAsociado(@Param("idEquipo") int idEquipo);
    
    void setDarDeBaja(@Param("idEquipo") int idEquipo);

    List<Equipo> consultarEquiposNoDeBaja();
    
    void desasociarEquipoDelLab(@Param("idEquipo") int idEquipo);

    List<Equipo> consultarEquiposTodo();

    List<Novedad> consultarNovedadEquipo(@Param("idEquipo") int idEquipo);
}