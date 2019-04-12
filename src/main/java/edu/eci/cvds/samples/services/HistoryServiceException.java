package edu.eci.cvds.samples.services;

import javax.persistence.PersistenceException;

public class HistoryServiceException extends Exception {

    public HistoryServiceException(String msg){
        super(msg);
    }

    public HistoryServiceException(String error_al_registar_el_equipo, PersistenceException e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
