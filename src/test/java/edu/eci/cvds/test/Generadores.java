/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.test;

import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.TipoElemento;
import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;
import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.strings;
/**
 *
 * @author 2131381
 */
public class Generadores {
    
    public static Gen<Integer> ids(){
        return integers().between(1,1000);
    } 
    
    public static Gen<TipoElemento> genTipoElementos(){
        return Generate.enumValues(TipoElemento.class);
    }
    
    public static Gen<String> genMarcas(){
        return strings().betweenCodePoints(97,122).ofLengthBetween(5, 10);
    }
    
    public static Gen<String> genDescripcion(){
        return strings().betweenCodePoints(97,122).ofLengthBetween(20, 100);
    }
    
    public static Gen<Boolean> genDisponible(){
        return Generate.booleans();
    }
    
    public static Gen<Elemento> genElementos(){
        return source->{
            int ids = ids().generate(source);
            TipoElemento tipElement = genTipoElementos().generate(source);
            String marca = genMarcas().generate(source);
            String descripcion = genDescripcion().generate(source);
            boolean disponible = genDisponible().generate(source);
            return new Elemento(ids,tipElement,marca,descripcion,disponible);            
        };   
    }
    
    public static Gen<Elemento> genElementosMouse(){
        return source->{
            int ids = ids().generate(source);
            TipoElemento mouse = TipoElemento.MOUSE;
            String marca = genMarcas().generate(source);
            String descripcion = genDescripcion().generate(source);
            boolean disponible = genDisponible().generate(source);
            return new Elemento(ids,mouse,marca,descripcion,disponible);            
        };   
    }
    public static Gen<Elemento> genElementosTorre(){
        return source->{
            int ids = ids().generate(source);
            TipoElemento torre = TipoElemento.TORRE;
            String marca = genMarcas().generate(source);
            String descripcion = genDescripcion().generate(source);
            boolean disponible = genDisponible().generate(source);
            return new Elemento(ids,torre,marca,descripcion,disponible);            
        };   
    }
    public static Gen<Elemento> genElementosPantalla(){
        return source->{
            int ids = ids().generate(source);
            TipoElemento pantalla = TipoElemento.PANTALLA;
            String marca = genMarcas().generate(source);
            String descripcion = genDescripcion().generate(source);
            boolean disponible = genDisponible().generate(source);
            return new Elemento(ids,pantalla,marca,descripcion,disponible);            
        };   
    }
    public static Gen<Elemento> genElementosTeclado(){
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
            int eId = ids().generate(source);
            Elemento torre = genElementosTorre().generate(source);
            Elemento mouse = genElementosMouse().generate(source);
            Elemento pantalla = genElementosPantalla().generate(source);
            Elemento teclado = genElementosTeclado().generate(source);
            return new Equipo(eId,torre, pantalla, mouse, teclado);            
        };   
    }
}