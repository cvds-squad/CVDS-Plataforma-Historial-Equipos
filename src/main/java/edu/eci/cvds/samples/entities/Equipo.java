/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.samples.entities;

import java.io.Serializable;

/**
 *
 * @author jcortes
 */
public class Equipo implements Serializable{
  
    private int idEquipo;
    private Elemento torre;
    private Elemento pantalla;
    private Elemento mouse;
    private Elemento teclado;
    private boolean disponible;

    public Equipo(int idEquipo){
        this.idEquipo = idEquipo;
    }

    public Equipo(int idEquipo,String novedad,boolean disponible){
        this.idEquipo = idEquipo;
        this.disponible = disponible;
    }

    public Equipo(int idEquipo, Elemento torre, Elemento pantalla, Elemento mouse, Elemento teclado) {
        this.idEquipo = idEquipo;
        this.torre = torre;
        this.pantalla = pantalla;
        this.mouse = mouse;
        this.teclado = teclado;
        this.disponible = true; //al crear siempre es disponible
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Elemento getTorre() {
        return torre;
    }

    public void setTorre(Elemento torre) {
        this.torre = torre;
    }

    public Elemento getPantalla() {
        return pantalla;
    }

    public void setPantalla(Elemento pantalla) {
        this.pantalla = pantalla;
    }

    public Elemento getMouse() {
        return mouse;
    }

    public void setMouse(Elemento mouse) {
        this.mouse = mouse;
    }

    public Elemento getTeclado() {
        return teclado;
    }

    public void setTeclado(Elemento teclado) {
        this.teclado = teclado;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    
    
    
}
