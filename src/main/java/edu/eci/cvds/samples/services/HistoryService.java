package edu.eci.cvds.samples.services;

import edu.eci.cvds.samples.entities.*;

public interface HistoryService {

    public abstract void registrarElemento(Elemento elem) throws HistoryServiceException;

}
