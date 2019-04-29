/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cvds.samples.entities;

import java.io.Serializable;

/**
 */
public class Elemento implements Serializable {
    
    private int idElemento;
    private TipoElemento tipelement;
    private String marca;
    private String descripcion;
    private boolean disponible;
    private Integer equipoAsociado;
    private boolean de_baja; 
    
    public Elemento(){
    
    }

    public Elemento(TipoElemento tipElement, String marca, String descripcion){
        this.tipelement = tipElement;
        this.marca = marca;
        this.descripcion = descripcion;
        this.disponible = true; //Al crear un elemento siempre esta disponible
    }

    public Elemento(int id,TipoElemento tipElement, String marca, String descripcion){
        this.equipoAsociado = equipoAsociado;
        this.idElemento = id;
        this.tipelement = tipElement;
        this.marca = marca;
        this.descripcion = descripcion;
        this.disponible = true; //Al crear un elemento siempre esta disponible
    }

    public Elemento(int id,Integer equipoAsociado,TipoElemento tipElement, String marca, String descripcion){
        this.equipoAsociado = equipoAsociado;
        this.idElemento = id;
        this.tipelement = tipElement;
        this.marca = marca;
        this.descripcion = descripcion;
        this.disponible = true; //Al crear un elemento siempre esta disponible
    }

    public Elemento(int ids, TipoElemento tipElement, String marca, String descripcion, boolean disponible) {
        this.idElemento = ids;
        this.tipelement= tipElement;
        this.marca = marca;
        this.descripcion = descripcion;
        this.disponible = disponible;
    }
    
    public Elemento(int ids, TipoElemento tipElement, String marca, String descripcion, boolean disponible,boolean de_baja) {
        this.idElemento = ids;
        this.tipelement= tipElement;
        this.marca = marca;
        this.descripcion = descripcion;
        this.disponible = disponible;
        this.de_baja = de_baja;
    }

    public int getIdElemento() {
        return idElemento;
    }

    public void setIdElemento(int idElemento) {
        this.idElemento = idElemento;
    }

    public TipoElemento getTipelement() {
        return tipelement;
    }

    public void setTipelement(TipoElemento tipelement) {
        this.tipelement = tipelement;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }


    public Integer getEquipoAsociado() {
        return equipoAsociado;
    }

    public void setEquipoAsociado(Integer equipoAsociado) {
        this.equipoAsociado = equipoAsociado;
    }

    @Override
    public String toString(){
        return marca + ": " +descripcion;
    }
}
