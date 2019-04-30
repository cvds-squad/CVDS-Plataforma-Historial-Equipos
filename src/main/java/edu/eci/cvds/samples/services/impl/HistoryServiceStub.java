package edu.eci.cvds.samples.services.impl;


import edu.eci.cvds.samples.entities.*;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HistoryServiceStub implements HistoryService
{
    

    private final Map<Integer,Elemento> elementosRegistrados;
    private final Map<Integer,Elemento> nuevosElemenRegis;
    private final Map<Integer,Equipo> equipos;
    
    public HistoryServiceStub(){
        elementosRegistrados = new HashMap<>();
        nuevosElemenRegis = new HashMap<>();
        equipos = new HashMap<>();
        poblar();
    }
    @Override
    public void registrarElemento(Elemento elem) throws HistoryServiceException {
        if(!elementosRegistrados.containsKey(elem.getIdElemento())){
            elementosRegistrados.put(elem.getIdElemento(), elem);
        }
        else{
            throw new HistoryServiceException("El elemento con codigo " + elem.getIdElemento() + " ya está registrado");
        }
    }

    @Override
    public void registrarElementoConId(Elemento elem) throws HistoryServiceException {

    }

    @Override
    public Elemento consultarElemento(int id) throws HistoryServiceException {
        return elementosRegistrados.get(id);
    }

    @Override
    public void registarEquipo(Equipo equipo) throws HistoryServiceException {
        if (!equipos.containsKey(equipo.getIdEquipo())) {
            equipos.put(equipo.getIdEquipo(), equipo);
        } else {
                throw new HistoryServiceException("El equipo ya se encuentra registrado.");
        }
    }

    @Override
    public void asociarElementoConEquipo(int equipoId, int elementoId){

    }

    @Override
    public List<Equipo> consultarEquipos() throws HistoryServiceException {
        return null;
    }

    @Override
    public List<Elemento> consultarElementosDisponibles() throws HistoryServiceException {
        return null;
    }

    @Override
    public Equipo consultaEquipo(int idEquipo) throws HistoryServiceException{
        return null;
    }

    @Override
    public int getIdMaxElemento() throws HistoryServiceException{
        return 0;
    }

    @Override
    public int getIdMaxEquipo() throws HistoryServiceException {
        return 0;
    }

    @Override
    public List<Elemento> consultarElementos() {
        return null;
    }

    @Override
    public List<Elemento> consultarTorresDisponibles() {
        return null;
    }

    @Override
    public List<Elemento> consultarPantallasDisponibles() throws HistoryServiceException {
        return null;
    }

    @Override
    public List<Elemento> consultarTecladosDisponibles() throws HistoryServiceException {
        return null;
    }

    @Override
    public List<Elemento> consultarMousesDisponibles() throws HistoryServiceException {
        return null;
    }
    
        @Override
    public void registrarNovedad(Novedad novedad) throws HistoryServiceException {

    }

    @Override
    public Novedad consultarNovedadDadoId(int idNovedad) throws HistoryServiceException {
        return null;
    }

    @Override
    public Novedad consultarNovedadDadoEquipo(int idEquipo) throws HistoryServiceException {
        throw null;
    }

    @Override
    public Novedad consultarNovedadDadoElementoEquipo(int idElemento, int idEquipo) throws HistoryServiceException {
        throw null;
    }
    
        @Override
    public void registrarLaboratorio(Laboratorio laboratorio) throws HistoryServiceException {
        
    }

    @Override
    public Laboratorio consultarLaboratorio(int idLab) throws HistoryServiceException {
        throw null;
    }

    @Override
    public int getIdMaxLaboratorio() {
        return 0;
    }

    @Override
    public void asociarEquipoConLaboratorio(int maxLabId, int idEquipo) throws HistoryServiceException {

    }

    @Override
    public List<Laboratorio> consultarLaboratorios() throws HistoryServiceException {
        return null;
    }

    @Override
    public Integer consultarLabAsociadoAEquipo(int idE) throws HistoryServiceException {
        return null;
    }

    //Poblar
    private void poblar(){
        TipoElemento TE1 = TipoElemento.MOUSE;
        TipoElemento TE2 = TipoElemento.PANTALLA;
        TipoElemento TE3 = TipoElemento.TORRE;
        TipoElemento TE4 = TipoElemento.TECLADO;
        
        Elemento Elemento1 = new Elemento(-1, TE4, "hp", "Teclado clásico HP con cable "
                + "(con disposición para inglés de internacional): un teclado de estilo clásico que sobresale en lo que se refiere a sencillez y comodidad",true);
        
        Elemento Elemento2 = new Elemento(-2, TE1, "hp", "onstruido según directrices y normas estrictas,  "
                + "(el ratón HP combina sin esfuerzo un diseño moderno y elegante con funciones avanzadas que mejoran la vida.",true);
        
        Elemento Elemento3 = new Elemento(-3, TE2, "asus", "IPS, 27 pulgadas, resolución WQHD 2560 x 1440  "
                + "y 178 grados de ángulo de visión.",false);
        
        Elemento Elemento4 = new Elemento(-4, TE3, "asus", "Servidor tipo torre de nive corporativo con funciones para mayor confiabilidad,  "
                + "como memoria código de corrección de errores y controlador de almacenamiento integrado.",true);
        
        elementosRegistrados.put(-1, Elemento1);
        elementosRegistrados.put(-2, Elemento2);
        elementosRegistrados.put(-3, Elemento3);
        elementosRegistrados.put(-4, Elemento4);
        nuevosElemenRegis.put(-1, Elemento1);
        nuevosElemenRegis.put(-2, Elemento2);
        nuevosElemenRegis.put(-4, Elemento4);
    }   

    @Override
    public void darBajaElemento(int id) throws HistoryServiceException {
       if (elementosRegistrados.containsKey(id)){
           Elemento el = elementosRegistrados.get(id);
           el.setDe_baja(true);
       }
    }

    @Override
    public void darBajaEquipo(int idEquipo) throws HistoryServiceException {
        if (equipos.containsKey(idEquipo)){
           Equipo eqp = equipos.get(idEquipo);
           eqp.setDe_baja(true);
       }
    }

    @Override
    public List<Elemento> consultarElementosNoAsociados() throws HistoryServiceException {
        return null;
    }

    @Override
    public List<Elemento> consultarElementosDadosDeBaja() throws HistoryServiceException {
        return null;
    }

    @Override
    public void quitarAsociacionConEquipo(int idElemento) throws HistoryServiceException {

    }

    @Override
    public List<Equipo> consultarEquiposNoDeBaja() throws HistoryServiceException {
        return null;
    }

    @Override
    public List<Novedad> consultarNovedades() throws HistoryServiceException {
        return null;
    }

}
