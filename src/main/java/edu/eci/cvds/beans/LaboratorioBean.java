package edu.eci.cvds.beans;

import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import edu.eci.cvds.samples.entities.Novedad;
import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import edu.eci.cvds.samples.services.HistoryServicesFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name="laboratorioBean")
@SessionScoped
public class LaboratorioBean implements Serializable {

    private HistoryService historyService;

    private String labNombre;
    private String usuario;
    private String labDescripcion;
    private List<Equipo> labEquipos;

    public LaboratorioBean(){
        historyService = HistoryServicesFactory.getInstance().getHistoryService();
    }

    public void crearLaboratorio(){
        Laboratorio laboratorio = new Laboratorio(labNombre,ShiroSecurityBean.getUser(),labDescripcion,(ArrayList<Equipo>)labEquipos);
        Date utilDate = new Date();
        try {
            historyService.registrarLaboratorio(laboratorio);
            int maxLabId = historyService.getIdMaxLaboratorio();
            for (Equipo equipo : labEquipos){
                Integer labAnterior = historyService.consultarLabAsociadoAEquipo(equipo.getIdEquipo());
                historyService.asociarEquipoConLaboratorio(maxLabId,equipo.getIdEquipo());
                historyService.registrarNovedad(new Novedad(null,equipo.getIdEquipo(),new java.sql.Date(utilDate.getTime()),"Asociación a laboratorio",ShiroSecurityBean.getUser(),"Se asoció al laboratorio " + labNombre + " el equipo con id: " + equipo.getIdEquipo()));
                if ( labAnterior != null){
                    historyService.registrarNovedad(new Novedad(null,equipo.getIdEquipo(), new java.sql.Date(utilDate.getTime()),"Desasoción de laboratorio",ShiroSecurityBean.getUser(),"Se desasoció del laboratorio " + historyService.consultarLaboratorio(labAnterior).getNombre() + " el equipo con id: " + equipo.getIdEquipo()));
                }
            }
            clean();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Laboratorio registrado correctamente","Laboratorio registrado correctamente"));
        } catch (HistoryServiceException e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se pudo registrar el laboratorio","No se pudo registrar el laboratorio"));
        }
    }

    public List<Laboratorio> consultarLaboratorios(){
        List<Laboratorio> laboratorios = null;
        try{
           laboratorios = historyService.consultarLaboratorios();
        }catch (HistoryServiceException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se pudo consultar laboratorios","No se pudo consultar laboratorios"));
        }
        return laboratorios;
    }

    private void clean() {
        labNombre = "";
        labDescripcion = "";
    }


    public String getLabNombre() {
        return labNombre;
    }

    public void setLabNombre(String labNombre) {
        this.labNombre = labNombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getLabDescripcion() {
        return labDescripcion;
    }

    public void setLabDescripcion(String labDescripcion) {
        this.labDescripcion = labDescripcion;
    }

    public List<Equipo> getLabEquipos() {
        return labEquipos;
    }

    public void setLabEquipos(List<Equipo> labEquipos) {
        this.labEquipos = labEquipos;
    }
}
