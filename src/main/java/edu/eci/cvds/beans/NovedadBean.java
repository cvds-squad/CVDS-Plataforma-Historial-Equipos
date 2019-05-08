package edu.eci.cvds.beans;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Novedad;
import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import edu.eci.cvds.samples.services.HistoryServicesFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class NovedadBean implements Serializable {

    private HistoryService historyService;

    private Elemento elementoSeleccionado;

    private Equipo equipoSeleccionado;

    private String elementoNovedadDescripcion;

    private String elementoNovedadTitulo;

    private String equipoNovedadTitulo;

    private String equipoNovedadDescripcion;

    private List<Novedad> todosNovedades;

    public NovedadBean(){
        historyService = HistoryServicesFactory.getInstance().getHistoryService();
    }

    public Elemento getElementoSeleccionado() {
        return elementoSeleccionado;
    }

    public void setElementoSeleccionado(Elemento elementoSeleccionado) {
        this.elementoSeleccionado = elementoSeleccionado;
    }

    /**
     * Registra la novedad de un elemento seleccionado de una tabla
     * */
    public void registroNovedadElemento(){
        Date utilDate = new Date();
        try {
            if (elementoSeleccionado != null) {

                historyService.registrarNovedad(new Novedad(elementoSeleccionado.getIdElemento(), null, new java.sql.Date(utilDate.getTime()), elementoNovedadTitulo, ShiroSecurityBean.getUser(), elementoNovedadDescripcion));
                Integer equipAsociado = historyService.consultarElemento(elementoSeleccionado.getIdElemento()).getEquipoAsociado();
                if (equipAsociado != null){
                    Equipo equipo = historyService.consultaEquipo(equipAsociado);
                    historyService.registrarNovedad(new Novedad(null, equipo.getIdEquipo(), new java.sql.Date(utilDate.getTime()), elementoNovedadTitulo, ShiroSecurityBean.getUser(), elementoNovedadDescripcion));
                }
                FacesContext.getCurrentInstance().addMessage("elemento", new FacesMessage(FacesMessage.SEVERITY_INFO, "Novedad registrada correctamente", "Novedad registrada correctamente"));
                cleanElemento();
            } else {
                FacesContext.getCurrentInstance().addMessage("elemento", new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione un elemento", "Seleccione un elemento"));
            }
        }catch (HistoryServiceException e) {
            FacesContext.getCurrentInstance().addMessage("elemento", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar la novedad", "Error al registrar la novedad"));
        }
    }

    /**
     * Registra la novedad de un equipo seleccionado de una tabla
     * */
    public void registroNovedadEquipo(){
        Date utilDate = new Date();
        try {
            if (equipoSeleccionado != null) {

                historyService.registrarNovedad(new Novedad(null, equipoSeleccionado.getIdEquipo(), new java.sql.Date(utilDate.getTime()), equipoNovedadTitulo, ShiroSecurityBean.getUser(), equipoNovedadDescripcion));

                FacesContext.getCurrentInstance().addMessage("equipo", new FacesMessage(FacesMessage.SEVERITY_INFO, "Novedad registrada correctamente", "Novedad registrada correctamente"));
                cleanEquipo();
            } else {
                FacesContext.getCurrentInstance().addMessage("equipo", new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione un equipo", "Seleccione un equipo"));
            }
        }catch (HistoryServiceException e) {
            FacesContext.getCurrentInstance().addMessage("equipo", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error al registrar la novedad", "Error al registrar la novedad"));
        }
    }

    /**
     * Consulta todas las novedades
     * @return lista de todas las novedades
     */
    public List<Novedad> consultarNovedades(){
        List<Novedad> novedades = null;
        try{
            novedades = historyService.consultarNovedades();
        }catch (HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage("novedadesTabla", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al consultar las novedades","Error al consultar las novedades"));
        }
        return novedades;
    }

    //Limpia atributos
    private void cleanEquipo() {
        equipoNovedadDescripcion = "";
        equipoNovedadTitulo = "";
    }

    //Limpia atributos
    private void cleanElemento() {
        elementoNovedadTitulo = "";
        elementoNovedadDescripcion = "";
    }

    public String getElementoNovedadDescripcion() {
        return elementoNovedadDescripcion;
    }

    public void setElementoNovedadDescripcion(String elementoNovedadDescripcion) {
        this.elementoNovedadDescripcion = elementoNovedadDescripcion;
    }

    public String getElementoNovedadTitulo() {
        return elementoNovedadTitulo;
    }

    public void setElementoNovedadTitulo(String elementoNovedadTitulo) {
        this.elementoNovedadTitulo = elementoNovedadTitulo;
    }

    public Equipo getEquipoSeleccionado() {
        return equipoSeleccionado;
    }

    public void setEquipoSeleccionado(Equipo equipoSeleccionado) {
        this.equipoSeleccionado = equipoSeleccionado;
    }

    public String getEquipoNovedadTitulo() {
        return equipoNovedadTitulo;
    }

    public void setEquipoNovedadTitulo(String equipoNovedadTitulo) {
        this.equipoNovedadTitulo = equipoNovedadTitulo;
    }

    public String getEquipoNovedadDescripcion() {
        return equipoNovedadDescripcion;
    }

    public void setEquipoNovedadDescripcion(String equipoNovedadDescripcion) {
        this.equipoNovedadDescripcion = equipoNovedadDescripcion;
    }

    public List<Novedad> getTodosNovedades() {
        if (todosNovedades == null){
            todosNovedades = consultarNovedades();
        }
        return todosNovedades;
    }

    public void setTodosNovedades(List<Novedad> todosNovedades) {
        this.todosNovedades = todosNovedades;
    }
}
