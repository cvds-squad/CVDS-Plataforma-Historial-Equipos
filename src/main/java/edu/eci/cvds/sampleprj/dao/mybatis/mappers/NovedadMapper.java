/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import edu.eci.cvds.samples.entities.Novedad;
import org.apache.ibatis.annotations.Param;

/**
 *
 * @author jcortes
 */
public interface NovedadMapper {
    
    public void registrarNovedad(@Param("novedad") Novedad novedad);
}
