package edu.eci.cvds.beans;

import edu.eci.cvds.samples.entities.Elemento;

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

    private String elementoNovedadDescripcion;

    private String elementoNovedadTitulo;

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
}
