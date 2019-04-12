package edu.eci.cvds.samples.services;

import javax.persistence.PersistenceException;

public class HistoryServiceException extends Exception {

    public HistoryServiceException(String msg){
        super(msg);
    }
    
    public HistoryServiceException (String string, PersistenceException ex){
        super(string,ex);
    }
}
