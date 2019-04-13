package edu.eci.cvds.beans;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.TipoElemento;
import edu.eci.cvds.samples.services.HistoryService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="elementoBean")
@RequestScoped
public class ElementoBean {

    @Inject
    private HistoryService historyService;

    private TipoElemento tipoElemento;

    private String marca;

    private String descripcion;

    public ElementoBean(){

    }

    public void setTipoElemento(String tipoElemento){
        this.tipoElemento = TipoElemento.valueOf(tipoElemento);
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
}
