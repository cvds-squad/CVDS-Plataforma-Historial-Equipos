/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;

import java.sql.Date;
import java.util.List;
/**
 *
 * @author jcortes
 */
public interface LaboratorioDAO {
    
    public void registrarLaboratorio(Laboratorio laboratorio);
    
    public Laboratorio consultarLaboratorio (int idLab);

    int getMaxIdLaboratorio();

    void asociarEquipoConLaboratorio(int maxLabId, int idEquipo);

    List<Laboratorio> consultarLaboratorios();

    List<Equipo> consultarEquiposDeLaboratorio(int idLab);
    
    List<Laboratorio> consultarLaboratoriosAbiertos();
    
    List<Laboratorio> consultarLaboratoriosCerrados();

    void darBajaLaboratorio(int idLab, Date fcierre);
}
