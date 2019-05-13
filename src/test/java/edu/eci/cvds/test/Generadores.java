/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.test;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.Laboratorio;
import edu.eci.cvds.samples.entities.Novedad;
import edu.eci.cvds.samples.entities.TipoElemento;
import java.sql.Date;
import java.util.List;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

import static org.quicktheories.generators.SourceDSL.*;

/**
 *
 * @author 2131381
 */
public class Generadores {

    private static Gen<Integer> ids(){
        return integers().between(1,1000);
    }

    private static Gen<TipoElemento> genTipoElementos(){
        return Generate.enumValues(TipoElemento.class);
    }

    private static Gen<String> genMarcas(){
        return strings().betweenCodePoints(97,122).ofLengthBetween(5, 10);
    }

    private static Gen<String> genDescripcion(){
        return strings().betweenCodePoints(97,122).ofLengthBetween(20, 100);
    }

    private static Gen<Boolean> genDisponible(){
        return Generate.booleans();
    }

    private static Gen<Date> genDate(){
        return Generate.longRange(0,100).map(l -> new Date(l));
    }

    public static Gen<Elemento> genElementos(){
        return source->{
            TipoElemento tipElement = genTipoElementos().generate(source);
            String marca = genMarcas().generate(source);
            String descripcion = genDescripcion().generate(source);
            return new Elemento(HistoryServicesTest.idElemCont,tipElement,marca,descripcion);
        };
    }

    private static Gen<Elemento> genElementosMouse(){
        return source->{
            int ids = ids().generate(source);
            TipoElemento mouse = TipoElemento.MOUSE;
            String marca = genMarcas().generate(source);
            String descripcion = genDescripcion().generate(source);
            boolean disponible = genDisponible().generate(source);
            return new Elemento(ids,mouse,marca,descripcion,disponible);
        };
    }
    private static Gen<Elemento> genElementosTorre(){
        return source->{
            int ids = ids().generate(source);
            TipoElemento torre = TipoElemento.TORRE;
            String marca = genMarcas().generate(source);
            String descripcion = genDescripcion().generate(source);
            boolean disponible = genDisponible().generate(source);
            return new Elemento(ids,torre,marca,descripcion,disponible);
        };
    }
    private static Gen<Elemento> genElementosPantalla(){
        return source->{
            int ids = ids().generate(source);
            TipoElemento pantalla = TipoElemento.PANTALLA;
            String marca = genMarcas().generate(source);
            String descripcion = genDescripcion().generate(source);
            boolean disponible = genDisponible().generate(source);
            return new Elemento(ids,pantalla,marca,descripcion,disponible);
        };
    }
    private static Gen<Elemento> genElementosTeclado(){
        return source->{
            int ids = ids().generate(source);
            TipoElemento teclado = TipoElemento.TECLADO;
            String marca = genMarcas().generate(source);
            String descripcion = genDescripcion().generate(source);
            boolean disponible = genDisponible().generate(source);
            return new Elemento(ids,teclado,marca,descripcion,disponible);
        };
    }

    public static Gen<Equipo> genEquipos(){
        return source->{
            Elemento torre = genElementosTorre().generate(source);
            Elemento mouse = genElementosMouse().generate(source);
            Elemento pantalla = genElementosPantalla().generate(source);
            Elemento teclado = genElementosTeclado().generate(source);
            return new Equipo(HistoryServicesTest.idEquipoCont,torre, pantalla, mouse, teclado);
        };
    }
    public static Gen<Novedad> genNovedades(){
        return source->{
            int equipo = genEquipos().generate(source).getIdEquipo();
            int elemento = genElementos().generate(source).getIdElemento();
            Date fecha = genDate().generate(source);
            String titulo = genMarcas().generate(source);
            String responsable = genDescripcion().generate(source);
            String detalle = genDescripcion().generate(source);
            return new Novedad(equipo, elemento, fecha, titulo, responsable, detalle);
        };
    }

    public static Gen<Novedad> genNovedadesNoFk(){
        return source->{
            Integer equipo = null;
            Integer elemento = null;
            Date fecha = genDate().generate(source);
            String titulo = genMarcas().generate(source);
            String responsable = "predeterminado";
            String detalle = genDescripcion().generate(source);
            return new Novedad(equipo, elemento, fecha, titulo, responsable, detalle);
        };
    }

    public static Gen<Laboratorio> genLaboratorios(){
        return source->{
            String nombre = genMarcas().generate(source);
            String usuario = "predeterminado";
            String descripcion = genDescripcion().generate(source);
            boolean openclosed = genDisponible().generate(source);
            return new Laboratorio(HistoryServicesTest.idLaboratory, nombre, usuario, descripcion,openclosed);
        };
    }

    public static Gen<Laboratorio> genOpenLaboratorios(){
        return source->{
            String nombre = genMarcas().generate(source);
            String usuario = "predeterminado";
            String descripcion = genDescripcion().generate(source);
            return new Laboratorio(HistoryServicesTest.idLaboratory, nombre, usuario, descripcion,true);
        };
    }

    public static Gen<List<Laboratorio>> genLabArray(){
        return source -> {
            int size = integers().between(1,5).generate(source);
            return lists().of(genLaboratorios()).ofSize(size).generate(source);
        };
    }
}
