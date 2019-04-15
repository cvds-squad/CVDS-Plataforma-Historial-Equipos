package edu.eci.cvds.beans;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.TipoElemento;
import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import edu.eci.cvds.samples.services.HistoryServicesFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ManagedBean(name="equipoBean")
@SessionScoped
public class EquipoBean implements Serializable {

    private HistoryService historyService;

    private Equipo equipo;

    public EquipoBean(){
        historyService = HistoryServicesFactory.getInstance().getHistoryService();
    }

    public List<Equipo> consultarEquipos(){
        List<Equipo> equipos = null;
        try {
            equipos = historyService.consultarEquipos();
        } catch (HistoryServiceException e) {
            e.printStackTrace();
        }
        return equipos;
    }

    public void asociar(List<Elemento> elementos){
        Set<TipoElemento> set = new HashSet<>();
        for (Elemento elemento : elementos){
            TipoElemento tipoElemento = elemento.getTipelement();
            if (!set.contains(tipoElemento)) set.add(elemento.getTipelement());
            else{
                FacesContext.getCurrentInstance().addMessage("msgsElemento",new FacesMessage(FacesMessage.SEVERITY_ERROR,"No puede asociar dos elementos del mismo tipo","No puede asociar dos elementos del mismo tipo"));
                return;
            }
        }

        for (Elemento elemento : elementos){
            try{
                historyService.asociarElementoConEquipo(equipo.getIdEquipo(),elemento.getIdElemento());
            }catch (HistoryServiceException e){
                FacesContext.getCurrentInstance().addMessage("msgsElemento",new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al asociar elemento","Error al asociar elemento"));
                return;
            }
        }
        FacesContext.getCurrentInstance().addMessage("msgsElemento",new FacesMessage(FacesMessage.SEVERITY_INFO,"Elementos asociados correctamente","Elementos asociados correctamente"));
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }
}
