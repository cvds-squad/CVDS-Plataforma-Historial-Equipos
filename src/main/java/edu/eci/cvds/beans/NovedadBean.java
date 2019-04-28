package edu.eci.cvds.beans;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class NovedadBean implements Serializable {

    private Elemento elementoSeleccionado;

    private Equipo equipoSeleccionado;

    private String elementoNovedadDescripcion;

    private String elementoNovedadTitulo;

    private String equipoNovedadTitulo;

    private String equipoNovedadDescripcion;

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
        if (elementoSeleccionado != null){
            FacesContext.getCurrentInstance().addMessage("elemento", new FacesMessage(FacesMessage.SEVERITY_INFO,"Servicio en construccion","Servicio en construccion"));
            cleanElemento();
        }
        else {
            FacesContext.getCurrentInstance().addMessage("elemento", new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione un elemento", "Seleccione un elemento"));
        }
    }

    /**
     * Registra la novedad de un equipo seleccionado de una tabla
     * */
    public void registroNovedadEquipo(){
        System.out.println(equipoSeleccionado + " " +equipoNovedadTitulo + " " + equipoNovedadDescripcion);
        if (equipoSeleccionado != null){
            FacesContext.getCurrentInstance().addMessage("equipo", new FacesMessage(FacesMessage.SEVERITY_INFO,"Servicio en construccion","Servicio en construccion"));
            cleanEquipo();
        }
        else {
            FacesContext.getCurrentInstance().addMessage("equipo", new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione un equipo", "Seleccione un equipo"));
        }
    }

    private void cleanEquipo() {
        equipoNovedadDescripcion = "";
        equipoNovedadTitulo = "";
    }

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
}
