/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.samples.entities;

import java.sql.Date;

public class Novedad {
    
    private int elementoAsociado;
    private int equipoAsociado;
    private Date fecha;
    private String titulo;
    private String responsable;
    private String detalle;

    public Novedad(int elementoAsociado, int equipoAsociado, Date fecha, String titulo, String responsable, String detalle) {
        this.elementoAsociado = elementoAsociado;
        this.equipoAsociado = equipoAsociado;
        this.fecha = fecha;
        this.titulo = titulo;
        this.responsable = responsable;
        this.detalle = detalle;
    }
    

    public int getElementoAsociado() {
        return elementoAsociado;
    }

    public void setElementoAsociado(int elementoAsociado) {
        this.elementoAsociado = elementoAsociado;
    }

    public int getEquipoAsociado() {
        return equipoAsociado;
    }

    public void setEquipoAsociado(int equipoAsociado) {
        this.equipoAsociado = equipoAsociado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }
    
    
}
