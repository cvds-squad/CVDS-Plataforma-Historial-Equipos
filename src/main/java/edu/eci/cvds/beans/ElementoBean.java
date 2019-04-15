package edu.eci.cvds.beans;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.TipoElemento;
import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import edu.eci.cvds.samples.services.HistoryServicesFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;


@ManagedBean(name="elementoBean")
@RequestScoped
public class ElementoBean {

    private HistoryService historyService;

    private TipoElemento tipoElemento;

    private String marca;

    private String descripcion;

    private List<Elemento> elementosDisponibles;

    public ElementoBean(){
        historyService = HistoryServicesFactory.getInstance().getHistoryService();
    }

    public void setTipoElemento(TipoElemento tipoElemento){
        this.tipoElemento = tipoElemento;
    }

    public TipoElemento getTipoElemento(){
        return tipoElemento;
    }

    public void setMarca(String marca){
        this.marca = marca;
    }

    public String getMarca(){
        return marca;
    }

    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }

    public TipoElemento[] tipos(){
        return TipoElemento.values();
    }


    public void registro() {
        Elemento elemento = new Elemento(tipoElemento,marca,descripcion);
        String msg;
        try {
            historyService.registrarElemento(elemento);
            msg = "Elemento registrado correctamente";
            setDescripcion(null);
            setMarca(null);
            setTipoElemento(null);
        } catch (HistoryServiceException e) {
            msg = "Fallo al registrar el elemento";
        }
        FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,msg,msg));
    }

    public List<Elemento> consultarElementosDisponibles(){
        List<Elemento> elems = null;
        try {
            elems = historyService.consultarElementosDisponibles();
        } catch (HistoryServiceException e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al consultar elementos disponibles","Error al consultar elementos disponibles"));
        }
        return elems;
    }

    public List<Elemento> getElementosDisponibles() {
        return elementosDisponibles;
    }

    public void setElementosDisponibles(List<Elemento> elementosDisponibles) {
        this.elementosDisponibles = elementosDisponibles;
    }

}
