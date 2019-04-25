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

    private String marcaTorre;
    private String descTorre;

    private String marcaPantalla;
    private String descPantalla;

    private String marcaMouse;
    private String descMouse;

    private String marcaTeclado;
    private String descTeclado;

    public EquipoBean(){
        historyService = HistoryServicesFactory.getInstance().getHistoryService();
    }

    /**
     * Consulta todos los equipos
     * @return lista de todos los equipos registrados
     **/
    public List<Equipo> consultarEquipos(){
        List<Equipo> equipos = null;
        try {
            equipos = historyService.consultarEquipos();
        } catch (HistoryServiceException e) {
            e.printStackTrace();
        }
        return equipos;
    }

    /**
     * Crea un equipo, el equipo siempre tiene torre,mouse,teclado y pantalla
     **/
    public void crearEquipo(){
        try{
            historyService.registarEquipo(new Equipo());

            int maxIdElemento = historyService.getIdMaxElemento();
            int maxIdEquipo = historyService.getIdMaxEquipo();

            Elemento torre = new Elemento(TipoElemento.TORRE,marcaTorre,descTorre);
            historyService.registrarElemento(torre);
            historyService.asociarElementoConEquipo(maxIdEquipo,++maxIdElemento);

            Elemento mouse = new Elemento(TipoElemento.MOUSE,marcaMouse,descMouse);
            historyService.registrarElemento(mouse);
            historyService.asociarElementoConEquipo(maxIdEquipo,++maxIdElemento);

            Elemento pantalla = new Elemento(TipoElemento.PANTALLA,marcaPantalla,descPantalla);
            historyService.registrarElemento(pantalla);
            historyService.asociarElementoConEquipo(maxIdEquipo,++maxIdElemento);

            Elemento teclado = new Elemento(TipoElemento.TECLADO,marcaTeclado,descTeclado);
            historyService.registrarElemento(teclado);
            historyService.asociarElementoConEquipo(maxIdEquipo,++maxIdElemento);
            cleanElementos();
            FacesContext.getCurrentInstance().addMessage("registroEquipo",new FacesMessage(FacesMessage.SEVERITY_INFO,"Equipo registrado correctamente","Equipo registrado correctamente"));
        }catch (HistoryServiceException ex){
            FacesContext.getCurrentInstance().addMessage("registroEquipo", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al crear el nuevo equipo","Error al crear el nuevo equipo"));
        }
    }

    /**
     * Metodo privado para limpiar los atributos
     **/
    private void cleanElementos() {
        marcaMouse = null; descMouse = null;
        marcaPantalla = null; descPantalla = null;
        marcaTeclado = null; descTeclado = null;
        marcaTorre = null; descTorre = null;
    }

    /**
     * Asocia elementos al equipo asignado en el atributo
     * @param elementos Lista de elementos a asignar
     **/
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
            System.out.println(equipo + " " + elemento);
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

    public String getMarcaTorre() {
        return marcaTorre;
    }

    public void setMarcaTorre(String marcaTorre) {
        this.marcaTorre = marcaTorre;
    }

    public String getDescTorre() {
        return descTorre;
    }

    public void setDescTorre(String descTorre) {
        this.descTorre = descTorre;
    }

    public String getMarcaPantalla() {
        return marcaPantalla;
    }

    public void setMarcaPantalla(String marcaPantalla) {
        this.marcaPantalla = marcaPantalla;
    }

    public String getDescPantalla() {
        return descPantalla;
    }

    public void setDescPantalla(String descPantalla) {
        this.descPantalla = descPantalla;
    }

    public String getMarcaMouse() {
        return marcaMouse;
    }

    public void setMarcaMouse(String marcaMouse) {
        this.marcaMouse = marcaMouse;
    }

    public String getDescMouse() {
        return descMouse;
    }

    public void setDescMouse(String descMouse) {
        this.descMouse = descMouse;
    }

    public String getMarcaTeclado() {
        return marcaTeclado;
    }

    public void setMarcaTeclado(String marcaTeclado) {
        this.marcaTeclado = marcaTeclado;
    }

    public String getDescTeclado() {
        return descTeclado;
    }

    public void setDescTeclado(String descTeclado) {
        this.descTeclado = descTeclado;
    }
}
