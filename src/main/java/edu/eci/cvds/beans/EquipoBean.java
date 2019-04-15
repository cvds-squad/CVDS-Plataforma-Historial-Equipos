package edu.eci.cvds.beans;

import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import edu.eci.cvds.samples.services.HistoryServicesFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@ManagedBean(name="equipoBean")
@RequestScoped
public class EquipoBean {

    private HistoryService historyService;

    private int idEquipo;

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


    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public void asociar(int idElemento){
        System.out.println(idElemento + " "+ idEquipo);
        try {
            historyService.asociarElementoConEquipo(idEquipo,idElemento);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO,"Elemento asociado correctamente", "Elemento asociado correctamente"));
        } catch (HistoryServiceException e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al asociar", "Error al asociar"));
        }
    }
}
