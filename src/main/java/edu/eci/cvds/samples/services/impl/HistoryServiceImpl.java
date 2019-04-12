package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject;
import edu.eci.cvds.sampleprj.dao.EquipoDAO;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import javax.persistence.PersistenceException;

public class HistoryServiceImpl implements HistoryService {
    
    @Inject
    private EquipoDAO equipoDAO;

    @Override
    public void registarEquipo(Equipo equipo) throws HistoryServiceException {
	try{
            equipoDAO.registrarEquipo(equipo);
	} catch (PersistenceException e) {
		throw new HistoryServiceException("Error al registar el equipo.",e);
	}
    }



}
