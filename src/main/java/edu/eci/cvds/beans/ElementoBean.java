package edu.eci.cvds.beans;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Novedad;
import edu.eci.cvds.samples.entities.TipoElemento;
import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import edu.eci.cvds.samples.services.HistoryServicesFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@ManagedBean(name="elementoBean")
@ViewScoped
public class ElementoBean implements Serializable {

    private HistoryService historyService;

    private TipoElemento tipoElemento;

    private String marca;

    private String descripcion;

    private List<Elemento> elementosDisponibles;

    private List<Elemento> elementosDarBajar;

    private List<Elemento> todosElementos;

    private Elemento elemNovSeleccionado;

    private Integer adminElemID;

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

    /**
     * Registra un nuevo elemento utilizando los datos de tipoElemento,marca,descripcion
     **/
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

    /**
     * Consulta elementos disponibles
     * @return Lista de elementos disponibles
     **/
    public List<Elemento> consultarElementosDisponibles(){
        List<Elemento> elems = null;
        try {
            elems = historyService.consultarElementosDisponibles();
        } catch (HistoryServiceException e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al consultar elementos disponibles","Error al consultar elementos disponibles"));
        }
        return elems;
    }

    /**
     * Consulta todos los elementos
     * @return Lista de todos los elementos registrados en base de datos
     **/
    public List<Elemento> consultarElementos(){
        List<Elemento> elems = null;
        try{
            elems = historyService.consultarElementos();
        }catch (HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al consultar los elementos","Error al consultar elementos en la base de datos"));
        }
        return elems;
    }

    /**
     * Consulta las torres disponibles
     * @return lista de torres disponibles
     */
    public List<Elemento> consultarTorresDisponibles(){
        List<Elemento> torresDisponibles = null;
        try{
            torresDisponibles = historyService.consultarTorresDisponibles();
        }catch(HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al consultar torres disponibles","Error al consultar torres disponibles"));
        }
        return torresDisponibles;
    }

    /**
     * Consulta las pantallas disponibles
     * @return lista de pantallas disponibles
     **/
    public List<Elemento> consultarPantallasDisponibles(){
        List<Elemento> pantallasDisponibles = null;
        try{
            pantallasDisponibles = historyService.consultarPantallasDisponibles();
        }catch(HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al consultar torres disponibles","Error al consultar torres disponibles"));
        }
        return pantallasDisponibles;
    }

    /**
     * Consulta las teclados disponibles
     * @return lista de teclados disponibles
     **/
    public List<Elemento> consultarTecladosDisponibles(){
        List<Elemento> tecladosDisponibles = null;
        try{
            tecladosDisponibles = historyService.consultarTecladosDisponibles();
        }catch(HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al consultar torres disponibles","Error al consultar torres disponibles"));
        }
        return tecladosDisponibles;
    }

    /**
     * Consulta las mouses disponibles
     * @return lista de mouses disponibles
     **/
    public List<Elemento> consultarMousesDisponibles(){
        List<Elemento> mousesDisponibles = null;
        try{
            mousesDisponibles = historyService.consultarMousesDisponibles();
        }catch(HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al consultar torres disponibles","Error al consultar torres disponibles"));
        }
        return mousesDisponibles;
    }

    /**
     * Consulta elementos no asociados a equipos
     * @return lista de elementos no asociados
     **/
    public List<Elemento> consultarElementosNoAsociados(){
        List<Elemento> elementos = null;
        try{
            elementos = historyService.consultarElementosNoAsociados();
        }catch (HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al consultar elementos no asociados","Error al consultar elementos no asociados"));
        }
        return elementos;
    }

    /**
     * Consulta elementos que se han dado de baja
     * @return lista de elementos dados de baja
     **/
    public List<Elemento> consultarElementosBajados(){
        List<Elemento> elementos = null;
        try{
            elementos = historyService.consultarElementosDadosDeBaja();
        }catch (HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al consultar elementos no asociados","Error al consultar elementos no asociados"));
        }
        return elementos;
    }

    /**
     * Da de baja al elemento escogido
     */
    public void darBajaElemento(){
        Date utilDate = new Date();
        try {
            if (elementosDarBajar.size() > 0) {
                for (Elemento elemento : elementosDarBajar){
                    historyService.registrarNovedad(new Novedad(elemento.getIdElemento(), null, new java.sql.Date(utilDate.getTime()), "Dar baja elemento", ShiroSecurityBean.getUser(), "Se dio de baja al elemento " + elemento));
                    historyService.darBajaElemento(elemento.getIdElemento());
                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Elementos dado de baja correctamente", "Elementos dado de baja correctamente"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione por lo menos un elemento", "Seleccione por lo menos un elemento"));
            }
        }catch (HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Error al intentar dar de baja al elemento", "Error al intentar dar de baja al elemento"));
        }
    }

    /**
     * Consulta las novedades del elemento seleccionado en el reporte
     * @return Lista con las novedades
     */
    public List<Novedad> consultarNovedadesDeSeleccionado(){
        List<Novedad> novedades = null;
        try{
            novedades = historyService.consultarNovedadesDeElemento(elemNovSeleccionado.getIdElemento());
        }catch (HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),e.getMessage()));
        }
        return novedades;
    }

    /**
     * Obtiene la el elemento escogido en el reporte
     * @return lista del con el elemento escogido en el reporte
     */
    public List<Elemento> obtenerAdminId(){
        Elemento elemento = null;
        try {
            elemento = historyService.consultarElemento(adminElemID);
        } catch (HistoryServiceException e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),e.getMessage()));
        }
        List<Elemento> elementos = new ArrayList<>();
        elementos.add(elemento);
        return elementos;
    }

    /**
     * Da baja al elemento seleccionado en el reporte
     */
    public void adminElemDarBaja(){
        Date utilDate = new Date();
        try{
            Elemento elemento = historyService.consultarElemento(adminElemID);
            if (elemento.isDe_baja()){
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Ya esta de baja el elemento","Ya esta de baja el elemento"));
            }
            else {
                historyService.darBajaElemento(adminElemID);
                historyService.registrarNovedad(new Novedad(elemento.getIdElemento(), null, new java.sql.Date(utilDate.getTime()), "Dar baja elemento", ShiroSecurityBean.getUser(), "Se dio de baja al elemento " + elemento));
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Elemento dado de baja correctamente","Elemento dado de baja correctamente"));
            }
        }catch (HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),e.getMessage()));
        }
    }

    public List<Elemento> getElementosDisponibles() {
        return elementosDisponibles;
    }

    public void setElementosDisponibles(List<Elemento> elementosDisponibles) {
        this.elementosDisponibles = elementosDisponibles;
    }

    public List<Elemento> getElementosDarBajar() {
        return elementosDarBajar;
    }

    public void setElementosDarBajar(List<Elemento> elementosDarBajar) {
        this.elementosDarBajar = elementosDarBajar;
    }

    public List<Elemento> getTodosElementos() {
        if (todosElementos == null){
            todosElementos = consultarElementos();
        }
        return todosElementos;
    }

    public void setTodosElementos(List<Elemento> todosElementos) {
        this.todosElementos = todosElementos;
    }

    public Elemento getElemNovSeleccionado() {
        return elemNovSeleccionado;
    }

    public void setElemNovSeleccionado(Elemento elemNovSeleccionado) {
        this.elemNovSeleccionado = elemNovSeleccionado;
    }

    public Integer getAdminElemID() {
        return adminElemID;
    }

    public void setAdminElemID(Integer adminElemID) {
        this.adminElemID = adminElemID;
    }
}
