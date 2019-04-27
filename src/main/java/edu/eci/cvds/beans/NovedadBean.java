package edu.eci.cvds.beans;

import edu.eci.cvds.samples.entities.Elemento;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class NovedadBean implements Serializable {

    private Elemento elementoSeleccionado;

    public Elemento getElementoSeleccionado() {
        return elementoSeleccionado;
    }

    public void setElementoSeleccionado(Elemento elementoSeleccionado) {
        this.elementoSeleccionado = elementoSeleccionado;
    }
}
