package edu.eci.cvds.beans;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Novedad;
import edu.eci.cvds.samples.entities.TipoElemento;
import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import edu.eci.cvds.samples.services.HistoryServicesFactory;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

@ManagedBean(name="equipoBean")
@SessionScoped
public class EquipoBean implements Serializable {

    private HistoryService historyService;

    private Map<String,List<String>> crearElementos;
    private Map<String,Elemento> asociarElementos;

    private Equipo equipo;

    private String marcaTorre;
    private String descTorre;

    private String marcaPantalla;
    private String descPantalla;

    private String marcaMouse;
    private String descMouse;

    private String marcaTeclado;
    private String descTeclado;

    private String checkTorre;
    private String checkPantalla;
    private String checkMouse;
    private String checkTeclado;

    private Elemento torreSeleccionada;
    private Elemento pantallaSeleccionada;
    private Elemento tecladoSeleccionado;
    private Elemento mouseSeleccionado;

    private Equipo equipoDarBaja;

    private boolean darBajaTorre;
    private boolean quitarAsociacionTorre;

    private boolean darBajaPantalla;
    private boolean quitarAsociacionPantalla;

    private boolean darBajaTeclado;
    private boolean quitarAsociacionTeclado;

    private boolean darBajaMouse;
    private boolean quitarAsociacionMouse;

    private List<Equipo> todosEquipos;

    private Equipo equiNovSeleccionado;

    private Integer adminEquipoID;

    public EquipoBean(){
        historyService = HistoryServicesFactory.getInstance().getHistoryService();
        crearElementos =  new HashMap<>();
        asociarElementos = new HashMap<>();
        checkTorre = "&#10008;";
        checkMouse = "&#10008;";
        checkPantalla = "&#10008;";
        checkTeclado = "&#10008;";
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
     * Consulta los equipos que no estan de baja
     * @return lista de equipos no dados de baja
     */
    public List<Equipo> consultarEquiposNoDeBaja(){
        List<Equipo> equipos = null;
        try{
            equipos = historyService.consultarEquiposNoDeBaja();
        }catch (HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage("registroEquipo", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al consultar equipos","Error al consultar equipos"));
        }
        return equipos;
    }

    /**
     * Crea un equipo, el equipo siempre tiene torre,mouse,teclado y pantalla
     **/
    public void crearEquipo(){
        try{
            String ok = "&#10004;";
            if (!checkMouse.equals(ok) || !checkPantalla.equals(ok) || !checkTorre.equals(ok) || !checkTeclado.equals(ok) ){
                FacesContext.getCurrentInstance().addMessage("registroEquipo", new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe asociar o crear todos los elementos","Debe asociar o crear todos los elementos"));
                return;
            }
            historyService.registarEquipo(new Equipo());

            Integer maxIdElemento = historyService.getIdMaxElemento();
            if (maxIdElemento == null) maxIdElemento = 1;
            int maxIdEquipo = historyService.getIdMaxEquipo();

            for (Map.Entry<String, Elemento> entry : asociarElementos.entrySet()) {
                historyService.asociarElementoConEquipo(maxIdEquipo,entry.getValue().getIdElemento());
            }

            for (Map.Entry<String,List<String>> entry: crearElementos.entrySet()){
                Elemento r = new Elemento(TipoElemento.valueOf(entry.getKey().toUpperCase()),entry.getValue().get(0),entry.getValue().get(1));
                historyService.registrarElemento(r);
                historyService.asociarElementoConEquipo(maxIdEquipo,++maxIdElemento);
            }
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
        checkTorre = "&#10008;";
        checkMouse = "&#10008;";
        checkPantalla = "&#10008;";
        checkTeclado = "&#10008;";
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
        Date utilDate = new Date();
        for (Elemento elemento : elementos){
            try{
                historyService.asociarElementoConEquipo(equipo.getIdEquipo(),elemento.getIdElemento());
                historyService.registrarNovedad(new Novedad(elemento.getIdElemento(),equipo.getIdEquipo(),new java.sql.Date(utilDate.getTime()),"Asociacion de elemento",ShiroSecurityBean.getUser(),"Se asocio al equipo con id "+ equipo.getIdEquipo() + " el elemento " + elemento ));
            }catch (HistoryServiceException e){
                FacesContext.getCurrentInstance().addMessage("msgsElemento",new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al asociar elemento","Error al asociar elemento"));
                return;
            }
        }
        FacesContext.getCurrentInstance().addMessage("msgsElemento",new FacesMessage(FacesMessage.SEVERITY_INFO,"Elementos asociados correctamente","Elementos asociados correctamente"));
    }

    /**
     * Crea un torre sin ingresarla a la base de datos
     **/
    public void crearTorre(){
        if (asociarElementos.containsKey("torre")) asociarElementos.remove("torre");
        if (crearElementos.containsKey("torre")) crearElementos.remove("torre");
        List<String> elems = new ArrayList<>();
        elems.add(marcaTorre);
        elems.add(descTorre);
        checkTorre = "&#10004;";
        crearElementos.put("torre",elems);
    }

    /**
     * Asocia una torre sin realizar el cambio en al base de datos
     **/
    public void asociarTorre(){
        if (torreSeleccionada == null){
            FacesContext.getCurrentInstance().addMessage("msgsElemento", new FacesMessage(FacesMessage.SEVERITY_ERROR,"No asoció torre","No asoció torre"));
        }else{
            checkTorre = "&#10004;";
            if (crearElementos.containsKey("torre")) crearElementos.remove("torre");
            if (asociarElementos.containsKey("torre")) asociarElementos.remove("torre");
            asociarElementos.put("torre",torreSeleccionada);
        }
    }

    /**
     * Crea un pantalla sin ingresarla a la base de datos
     **/
    public void crearPantalla(){
        if (asociarElementos.containsKey("pantalla")) asociarElementos.remove("pantalla");
        if (crearElementos.containsKey("pantalla")) crearElementos.remove("pantalla");
        List<String> elems = new ArrayList<>();
        elems.add(marcaPantalla);
        elems.add(descPantalla);
        checkPantalla = "&#10004;";
        crearElementos.put("pantalla",elems);
    }

    /**
     * Asocia una pantalla sin realizar el cambio en al base de datos
     **/
    public void asociarPantalla(){
        if (pantallaSeleccionada == null){
            FacesContext.getCurrentInstance().addMessage("msgsElemento", new FacesMessage(FacesMessage.SEVERITY_ERROR,"No asoció pantalla","No asoció pantalla"));
        }else{
            checkPantalla = "&#10004;";
            if (crearElementos.containsKey("pantalla")) crearElementos.remove("pantalla");
            if (asociarElementos.containsKey("pantalla")) asociarElementos.remove("pantalla");
            asociarElementos.put("pantalla",pantallaSeleccionada);
        }
    }

    /**
     * Crea un teclado sin ingresarla a la base de datos
     **/
    public void crearTeclado(){
        if (asociarElementos.containsKey("teclado")) asociarElementos.remove("teclado");
        if (crearElementos.containsKey("teclado")) crearElementos.remove("teclado");
        List<String> elems = new ArrayList<>();
        elems.add(marcaTeclado);
        elems.add(descTeclado);
        checkTeclado = "&#10004;";
        crearElementos.put("teclado",elems);
    }

    /**
     * Asocia una torre sin realizar el cambio en al base de datos
     **/
    public void asociarTeclado(){
        if (tecladoSeleccionado == null){
            FacesContext.getCurrentInstance().addMessage("msgsElemento", new FacesMessage(FacesMessage.SEVERITY_ERROR,"No asoció teclado","No asoció teclado"));
        }else{
            checkTeclado = "&#10004;";
            if (crearElementos.containsKey("teclado")) crearElementos.remove("teclado");
            if (asociarElementos.containsKey("teclado")) asociarElementos.remove("teclado");
            asociarElementos.put("teclado",tecladoSeleccionado);
        }
    }

    /**
     * Crea un mouse sin ingresarla a la base de datos
     **/
    public void crearMouse(){
        if (asociarElementos.containsKey("mouse")) asociarElementos.remove("mouse");
        if (crearElementos.containsKey("mouse")) crearElementos.remove("mouse");
        List<String> elems = new ArrayList<>();
        elems.add(marcaMouse);
        elems.add(descMouse);
        checkMouse = "&#10004;";
        crearElementos.put("mouse",elems);
    }

    /**
     * Asocia una torre sin realizar el cambio en al base de datos
     **/
    public void asociarMouse(){
        if (mouseSeleccionado == null){
            FacesContext.getCurrentInstance().addMessage("msgsElemento", new FacesMessage(FacesMessage.SEVERITY_ERROR,"No asoció mouse","No asoció mouse"));
        }else{
            checkMouse = "&#10004;";
            if (crearElementos.containsKey("mouse")) crearElementos.remove("mouse");
            if (asociarElementos.containsKey("mouse")) asociarElementos.remove("mouse");
            asociarElementos.put("mouse",mouseSeleccionado);
        }
    }

    /**
     * Da de baja a un equipo seleccionado de una tabla
     * Registra novedades
     **/
    public void darBajaEquipo() {
        Date utilDate = new Date();
        if (equipoDarBaja != null) {
            if (!darBajaMouse && !darBajaTeclado && !darBajaPantalla && !darBajaTorre &&
                    !quitarAsociacionMouse && !quitarAsociacionTeclado && !quitarAsociacionTorre && !quitarAsociacionPantalla) {
                FacesContext.getCurrentInstance().addMessage("equipoDarBaja", new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar los estados de los elementos", "Debe seleccionar los estados de los elementos"));
            } else {
                try {
                    historyService.darBajaEquipo(equipoDarBaja.getIdEquipo());
                    historyService.registrarNovedad(new Novedad(null, equipoDarBaja.getIdEquipo(), new java.sql.Date(utilDate.getTime()), "Dar de baja equipo", ShiroSecurityBean.getUser(), "Se dio de baja al equipo con id " + equipoDarBaja.getIdEquipo()));
                    Elemento torre = equipoDarBaja.getTorre();
                    Elemento pantalla = equipoDarBaja.getPantalla();
                    Elemento teclado = equipoDarBaja.getTeclado();
                    Elemento mouse = equipoDarBaja.getMouse();
                    if (darBajaTorre && !quitarAsociacionTorre) {
                        historyService.darBajaElemento(torre.getIdElemento());
                        historyService.registrarNovedad(new Novedad(torre.getIdElemento(),equipoDarBaja.getIdEquipo(),new java.sql.Date(utilDate.getTime()),"Dar de baja elemento",ShiroSecurityBean.getUser(),"Se dio de baja al elemento " + torre));
                    } else {
                        historyService.registrarNovedad(new Novedad(torre.getIdElemento(),equipoDarBaja.getIdEquipo(),new java.sql.Date(utilDate.getTime()),"Desasociar elemento",ShiroSecurityBean.getUser(),"Se desasocio el elemento " + torre + " del equipo con id " + equipoDarBaja.getIdEquipo()));
                        historyService.quitarAsociacionConEquipo(equipoDarBaja.getTorre().getIdElemento());
                    }

                    if (darBajaPantalla && !quitarAsociacionPantalla) {
                        historyService.darBajaElemento(equipoDarBaja.getPantalla().getIdElemento());
                        historyService.registrarNovedad(new Novedad(pantalla.getIdElemento(),equipoDarBaja.getIdEquipo(),new java.sql.Date(utilDate.getTime()),"Dar de baja elemento",ShiroSecurityBean.getUser(),"Se dio de baja al elemento " + pantalla));
                    } else {
                        historyService.registrarNovedad(new Novedad(pantalla.getIdElemento(),equipoDarBaja.getIdEquipo(),new java.sql.Date(utilDate.getTime()),"Desasociar elemento",ShiroSecurityBean.getUser(),"Se desasocio el elemento " + pantalla + " del equipo con id " + equipoDarBaja.getIdEquipo()));
                        historyService.quitarAsociacionConEquipo(equipoDarBaja.getPantalla().getIdElemento());
                    }

                    if (darBajaTeclado && !quitarAsociacionTeclado) {
                        historyService.registrarNovedad(new Novedad(teclado.getIdElemento(),equipoDarBaja.getIdEquipo(),new java.sql.Date(utilDate.getTime()),"Dar de baja elemento",ShiroSecurityBean.getUser(),"Se dio de baja al elemento " + teclado));
                        historyService.darBajaElemento(equipoDarBaja.getTeclado().getIdElemento());
                    } else {
                        historyService.registrarNovedad(new Novedad(teclado.getIdElemento(),equipoDarBaja.getIdEquipo(),new java.sql.Date(utilDate.getTime()),"Desasociar elemento",ShiroSecurityBean.getUser(),"Se desasocio el elemento " + teclado + " del equipo con id " + equipoDarBaja.getIdEquipo()));
                        historyService.quitarAsociacionConEquipo(equipoDarBaja.getTeclado().getIdElemento());
                    }

                    if (darBajaMouse && !quitarAsociacionMouse) {
                        historyService.registrarNovedad(new Novedad(mouse.getIdElemento(),equipoDarBaja.getIdEquipo(),new java.sql.Date(utilDate.getTime()),"Dar de baja elemento",ShiroSecurityBean.getUser(),"Se dio de baja al elemento " + mouse));
                        historyService.darBajaElemento(equipoDarBaja.getMouse().getIdElemento());
                    } else {
                        historyService.registrarNovedad(new Novedad(mouse.getIdElemento(),equipoDarBaja.getIdEquipo(),new java.sql.Date(utilDate.getTime()),"Desasociar elemento",ShiroSecurityBean.getUser(),"Se desasocio el elemento " + mouse + " del equipo con id " + equipoDarBaja.getIdEquipo()));
                        historyService.quitarAsociacionConEquipo(equipoDarBaja.getMouse().getIdElemento());
                    }
                    FacesContext.getCurrentInstance().addMessage("equipoDarBaja", new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipo dado de baja correctamente", "Equipo dado de baja correctamente"));
                    resetBooleans();
                } catch (HistoryServiceException e) {
                    FacesContext.getCurrentInstance().addMessage("equipoDarBaja", new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
                }
            }
        }else{
            FacesContext.getCurrentInstance().addMessage("equipoDarBaja", new FacesMessage(FacesMessage.SEVERITY_WARN, "Seleccione un equipo", "Seleccione un equipo"));
        }
    }

    //Metodo privado para resetear los checkbox
    private void resetBooleans() {
        darBajaTorre = false;
        quitarAsociacionTorre = false;

        darBajaPantalla = false;
        quitarAsociacionPantalla = false;

        darBajaTeclado = false;
        quitarAsociacionTeclado = false;

        darBajaMouse = false;
        quitarAsociacionMouse = false;
    }

    /**
     * Reinicia el checkbox de baja torre
     **/
    public void resetBajaTorre(){
        darBajaTorre = false;
    }

    /**
     * Reinicia el checkbox de baja pantalla
     **/
    public void resetBajaPantalla(){
        darBajaPantalla = false;
    }

    /**
     * Reinicia el checkbox de baja teclado
     **/
    public void resetBajaTeclado(){
        darBajaTeclado = false;
    }

    /**
     * Reinicia el checkbox de baja mouse
     **/
    public void resetBajaMouse(){
        darBajaMouse = false;
    }

    /**
     * Reinicia el checkbox de asociar torre
     **/
    public void resetAsociacionTorre(){
        quitarAsociacionTorre = false;
    }

    /**
     * Reinicia el checkbox de asociar pantalla
     **/
    public void resetAsociacionPantalla(){
        quitarAsociacionPantalla = false;
    }

    /**
     * Reinicia el checkbox de asociar Teclado
     **/
    public void resetAsociacionTeclado(){
        quitarAsociacionTeclado = false;
    }

    /**
     * Reinicia el checkbox de asociar mouse
     **/
    public void resetAsociacionMouse(){
        quitarAsociacionMouse = false;
    }

    /**
     * Obtiene el equipo seleccionado
     * @return Se obtiene una lista con el equipo seleccionado
     */
    public List<Equipo> obtenerAdminEquipo(){
        List<Equipo> list = new ArrayList<>();
        try {
            Equipo equipo = historyService.consultaEquipo(adminEquipoID);
            list.add(equipo);
        } catch (HistoryServiceException e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error al consultar","Error"));
        }
        return list;
    }

    /**
     * Consultar novedades del elementos seleccinado en el reporte
     * @return Novedades seleccionadas
     */
    public List<Novedad> consultarNovedadesDeSeleccionado(){
        List<Novedad> list = null;
        try{
            list = historyService.consultarNovedadesDeEquipo(equiNovSeleccionado.getIdEquipo());
        }catch (HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),e.getMessage()));
        }
        return list;
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

    public String getCheckTorre() {
        return checkTorre;
    }

    public void setCheckTorre(String checkTorre) {
        this.checkTorre = checkTorre;
    }

    public String getCheckPantalla() {
        return checkPantalla;
    }

    public void setCheckPantalla(String checkPantalla) {
        this.checkPantalla = checkPantalla;
    }

    public String getCheckMouse() {
        return checkMouse;
    }

    public void setCheckMouse(String checkMouse) {
        this.checkMouse = checkMouse;
    }

    public String getCheckTeclado() {
        return checkTeclado;
    }

    public void setCheckTeclado(String checkTeclado) {
        this.checkTeclado = checkTeclado;
    }

    public void setTorreSeleccionada(Elemento torreSeleccionada) {
        this.torreSeleccionada = torreSeleccionada;
    }

    public Elemento getTorreSeleccionada(){
        return torreSeleccionada;
    }

    public Elemento getPantallaSeleccionada() {
        return pantallaSeleccionada;
    }

    public void setPantallaSeleccionada(Elemento pantallaSeleccionada) {
        this.pantallaSeleccionada = pantallaSeleccionada;
    }

    public Elemento getTecladoSeleccionado() {
        return tecladoSeleccionado;
    }

    public void setTecladoSeleccionado(Elemento tecladoSeleccionado) {
        this.tecladoSeleccionado = tecladoSeleccionado;
    }

    public Elemento getMouseSeleccionado() {
        return mouseSeleccionado;
    }

    public void setMouseSeleccionado(Elemento mouseSeleccionado) {
        this.mouseSeleccionado = mouseSeleccionado;
    }

    public Equipo getEquipoDarBaja() {
        return equipoDarBaja;
    }

    public void setEquipoDarBaja(Equipo equipoDarBaja) {
        this.equipoDarBaja = equipoDarBaja;
    }

    public boolean isDarBajaTorre() {
        return darBajaTorre;
    }

    public void setDarBajaTorre(boolean darBajaTorre) {
        this.darBajaTorre = darBajaTorre;
    }

    public boolean isQuitarAsociacionTorre() {
        return quitarAsociacionTorre;
    }

    public void setQuitarAsociacionTorre(boolean quitarAsociacionTorre) {
        this.quitarAsociacionTorre = quitarAsociacionTorre;
    }

    public boolean isDarBajaPantalla() {
        return darBajaPantalla;
    }

    public void setDarBajaPantalla(boolean darBajaPantalla) {
        this.darBajaPantalla = darBajaPantalla;
    }

    public boolean isQuitarAsociacionPantalla() {
        return quitarAsociacionPantalla;
    }

    public void setQuitarAsociacionPantalla(boolean quitarAsociacionPantalla) {
        this.quitarAsociacionPantalla = quitarAsociacionPantalla;
    }

    public boolean isDarBajaTeclado() {
        return darBajaTeclado;
    }

    public void setDarBajaTeclado(boolean darBajaTeclado) {
        this.darBajaTeclado = darBajaTeclado;
    }

    public boolean isQuitarAsociacionTeclado() {
        return quitarAsociacionTeclado;
    }

    public void setQuitarAsociacionTeclado(boolean quitarAsociacionTeclado) {
        this.quitarAsociacionTeclado = quitarAsociacionTeclado;
    }

    public boolean isDarBajaMouse() {
        return darBajaMouse;
    }

    public void setDarBajaMouse(boolean darBajaMouse) {
        this.darBajaMouse = darBajaMouse;
    }

    public boolean isQuitarAsociacionMouse() {
        return quitarAsociacionMouse;
    }

    public void setQuitarAsociacionMouse(boolean quitarAsociacionMouse) {
        this.quitarAsociacionMouse = quitarAsociacionMouse;
    }

    public List<Equipo> getTodosEquipos() {
        if (todosEquipos == null){
            todosEquipos = consultarEquiposTodos();
        }
        return todosEquipos;
    }

    private List<Equipo> consultarEquiposTodos(){
        List<Equipo> list = null;
        try{
           list = historyService.consultarEquiposTodos();
        }catch (HistoryServiceException e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"No se pudo consultar","No se pudo consultar"));
        }
        return list;
    }

    public void setTodosEquipos(List<Equipo> todosEquipos) {
        this.todosEquipos = todosEquipos;
    }

    public Equipo getEquiNovSeleccionado() {
        return equiNovSeleccionado;
    }

    public void setEquiNovSeleccionado(Equipo equiNovSeleccionado) {
        this.equiNovSeleccionado = equiNovSeleccionado;
    }

    public Integer getAdminEquipoID() {
        return adminEquipoID;
    }

    public void setAdminEquipoID(Integer adminEquipoID) {
        this.adminEquipoID = adminEquipoID;
    }
}
