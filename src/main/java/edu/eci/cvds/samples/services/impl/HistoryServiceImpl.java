package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.ElementoDAO;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import javax.persistence.PersistenceException;

public class HistoryServiceImpl implements HistoryService {
    @Inject
    private ElementoDAO elementoDAO;

    @Override
    public void registrarElemento(Elemento elem) throws HistoryServiceException {
        try{
            elementoDAO.registerElemento(elem);
        }
        catch(PersistenceException ex){
            throw new HistoryServiceException("No fue posible registrar el elemento",ex);  
        }
    }
}
