package edu.eci.cvds.beans;

import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import edu.eci.cvds.samples.entities.Novedad;
import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import edu.eci.cvds.samples.services.HistoryServicesFactory;
import org.primefaces.model.chart.PieChartModel;


import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name="laboratorioBean")
@ViewScoped
public class LaboratorioBean implements Serializable {

    private HistoryService historyService;

    private String labNombre;
    private String usuario;
    private String labDescripcion;
    private List<Equipo> labEquipos;

    private Laboratorio labDesasociar;
    private Laboratorio labAsociar;
    private List<Equipo> labAsociarEquipos;
    private List<Equipo> labDesasociarEquipos;

    private Laboratorio labCerrar;

    private PieChartModel pieModel;

    private List<Laboratorio> todosLaboratorios;

    public LaboratorioBean(){

        historyService = HistoryServicesFactory.getInstance().getHistoryService();
        labAsociarEquipos = new ArrayList<>();
        labDesasociarEquipos = new ArrayList<>();
    }

    @PostConstruct
    public void init(){
        crearPieModelOC();
    }

    /**
     * Crea un laboratorio dado un nombre y una descripcion
     * Asocia equipos de la lista de labAsociarEquipos
     * Registra novedades
     **/
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

    /**
     * Consulta todos los laboratorios
     * @return lista de laboratorios registrados
     **/
    public List<Laboratorio> consultarLaboratorios(){
        List<Laboratorio> laboratorios = null;
        try{
            laboratorios = historyService.consultarLaboratorios();
        }catch (HistoryServiceException ex){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se pudo consultar laboratorios","No se pudo consultar laboratorios"));
        }
        return laboratorios;
    }

    /**
     * Asocia los equipos seleccionados al laboratorio seleccionado
     **/
    public void asociarEquipos(){
        Date utilDate = new Date();
        try{
            if (labAsociar != null) {
                if (labAsociarEquipos.size() != 0) {
                    for (Equipo equipo : labAsociarEquipos) {
                        Integer labAnterior = historyService.consultarLabAsociadoAEquipo(equipo.getIdEquipo());
                        historyService.registrarNovedad(new Novedad(null, equipo.getIdEquipo(), new java.sql.Date(utilDate.getTime()), "Asociación a laboratorio", ShiroSecurityBean.getUser(), "Se asoció al laboratorio " + labAsociar.getNombre() + " el equipo con id: " + equipo.getIdEquipo()));
                        if (labAnterior != null) {
                            historyService.registrarNovedad(new Novedad(null, equipo.getIdEquipo(), new java.sql.Date(utilDate.getTime()), "Desasoción de laboratorio", ShiroSecurityBean.getUser(), "Se desasoció del laboratorio " + historyService.consultarLaboratorio(labAnterior).getNombre() + " el equipo con id: " + equipo.getIdEquipo()));
                        }
                        historyService.asociarEquipoConLaboratorio(labAsociar.getIdLab(), equipo.getIdEquipo());
                    }
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipos asociados correctamente", "Equipos asociados correctamente"));
                }
                else{
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Seleccione por lo menos un equipo","Seleccione por lo menos un equipo"));
                }
            }
            else{
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,"Seleccione un laboratorio","Seleccione un laboratorio"));
            }
        }catch (HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),e.getMessage()));
        }
    }

    /**
     * Desasocia los equipos seleccionados del laboratorio seleccionado
     **/
    public void desasociarEquipos(){
        Date utilDate = new Date();
        try {
            if (labDesasociar != null) {
                if (labDesasociarEquipos.size() != 0) {
                    for (Equipo equipo : labDesasociarEquipos) {
                        historyService.desasociarEquipoDelLab(equipo.getIdEquipo());
                    }
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipos desasociados correctamente", "Equipos desasociados correctamente"));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione por lo menos un equipo", "Seleccione por lo menos un equipo"));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione un laboratorio", "Seleccione un laboratorio"));
            }
        }catch (HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),e.getMessage()));
        }
    }

    /**
     * Consulta los equipos de el laboratorio al que se le desea desasociar los equipos
     * @return Lista de equipos asociados a dicho laboratorio
     **/
    public List<Equipo> consultarEquiposDeLaboratorioDesasociar(){
        List<Equipo> equipos = null;
        try{

            if (labDesasociar != null)
                equipos = historyService.consultarEquiposDeLaboratorio(labDesasociar.getIdLab());

        }catch (HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null , new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al consultar los equipos del laboratorio","Error al consultar los equipos del laboratorio"));
        }
        return equipos;
    }

    /**
     * Cierra un laboratorio
     * Desasocia todos sus equipos
     **/
    public void cerrarLaboratorio(){
        if (labCerrar != null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Lab seleccionado " + labCerrar.getNombre(),"Construccion"));
        }else{
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_WARN,"Seleccione un laboratorio","Seleccione un laboratorio"));
        }
    }

    /**
     * Consulta los laboratorios abiertos
     * @return Lista de laboratorios abiertos
     **/
    public List<Laboratorio> consultarLaboratoriosAbiertos(){
        return null;
    }

    /**
     * Consulta los laboratorios cerrados
     * @return Lista de laboratorios cerrados
     **/
    public List<Laboratorio> consultarLaboratoriosCerrados(){
        return null;
    }

    //Crea un pie chart para laboratorios cerrados y abiertos
    private void crearPieModelOC() {
        pieModel = new PieChartModel();
        try {
            List<Laboratorio> laboratorios = historyService.consultarLaboratorios();
            int open = 0, closed = 0;
            for (Laboratorio laboratorio : laboratorios) {
                //if laboratorio.isDeBaja() closed++ else open++
            }
            pieModel.setTitle("Estados de Laboratorios");
            pieModel.set("Abierto",12); //cambiar a open
            pieModel.set("Cerrado",14); //cambiar a closed
            pieModel.setShowDataLabels(true);
            pieModel.setLegendPosition("e");

        } catch (HistoryServiceException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error mientras se creaba la grafica", "Error al consultar laboratorios"));
        }
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

    public Laboratorio getLabDesasociar() {
        return labDesasociar;
    }

    public void setLabDesasociar(Laboratorio labDesasociar) {
        this.labDesasociar = labDesasociar;
    }

    public Laboratorio getLabAsociar() {
        return labAsociar;
    }

    public void setLabAsociar(Laboratorio labAsociar) {
        this.labAsociar = labAsociar;
    }

    public List<Equipo> getLabAsociarEquipos() {
        return labAsociarEquipos;
    }

    public void setLabAsociarEquipos(List<Equipo> labAsociarEquipos) {
        this.labAsociarEquipos = labAsociarEquipos;
    }

    public List<Equipo> getLabDesasociarEquipos() {
        return labDesasociarEquipos;
    }

    public void setLabDesasociarEquipos(List<Equipo> labDesasociarEquipos) {
        this.labDesasociarEquipos = labDesasociarEquipos;
    }

    public Laboratorio getLabCerrar() {
        return labCerrar;
    }

    public void setLabCerrar(Laboratorio labCerrar) {
        this.labCerrar = labCerrar;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    public void setPieModel(PieChartModel pieModel) {
        this.pieModel = pieModel;
    }

    public List<Laboratorio> getTodosLaboratorios() {
        if (todosLaboratorios == null){
            todosLaboratorios = consultarLaboratorios();
        }
        return todosLaboratorios;
    }

    public void setTodosLaboratorios(List<Laboratorio> todosLaboratorios) {
        this.todosLaboratorios = todosLaboratorios;
    }
}
