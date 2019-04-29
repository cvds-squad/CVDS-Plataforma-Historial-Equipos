/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.samples.entities;

import java.util.ArrayList;

/**
 *
 * @author jcortes
 */
public class Laboratorio {
    
    private int idLab;
    private String nombre;
    private String  usuario;
    private String descripcion;
    private ArrayList<Equipo> equipos;

    public Laboratorio(int idLab, String nombre, String usuario, String descripcion) {
        this.idLab = idLab;
        this.nombre = nombre;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.equipos = new ArrayList<Equipo>();
    }

    public Laboratorio(String nombre, String usuario, String descripcion, ArrayList<Equipo> equipos){
        this.nombre = nombre;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.equipos = new ArrayList<Equipo>();
    }

    public Laboratorio(int idLab, String nombre, String usuario, String descripcion, ArrayList<Equipo> equipos) {
        this.idLab = idLab;
        this.nombre = nombre;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.equipos = equipos;
    }

    public int getIdLab() {
        return idLab;
    }

    public void setIdLab(int idLab) {
        this.idLab = idLab;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Equipo> getEquipos() {
        return equipos;
    }

    public void setEquipos(ArrayList<Equipo> equipos) {
        this.equipos = equipos;
    }
    
    
    
    
    
}
