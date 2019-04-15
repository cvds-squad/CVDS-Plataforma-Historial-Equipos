package edu.eci.cvds.test;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.entities.Equipo;
import edu.eci.cvds.samples.entities.TipoElemento;
import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import edu.eci.cvds.samples.services.HistoryServicesFactory;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.quicktheories.QuickTheory.qt;

public class HistoryServicesTest {

    @Inject
    private SqlSession sqlSession;

    private HistoryService historyService;

    public static int idElemCont = 1;
    public static int idEquipoCont = 1;

    public HistoryServicesTest(){
        historyService = HistoryServicesFactory.getInstance().getHistoryServiceForTesting();
    }

    @Test
    public void shouldInsertAndConsultNewElements(){
        qt().forAll(Generadores.genElementos()).check(elem ->{
            try{
                historyService.registrarElementoConId(elem);

                //System.out.println("REGISTRAR! " + elem+ " id: " + idElemCont);
                Elemento elemInserted = historyService.consultarElemento(idElemCont);

                //System.out.println("Registrado? " + elemInserted);
                idElemCont++;
                return  elem.getDescripcion().equals(elemInserted.getDescripcion()) &&
                        elem.getMarca().equals(elemInserted.getMarca()) &&
                        elem.getTipelement().equals(elemInserted.getTipelement());
            }catch (HistoryServiceException e){
                e.printStackTrace();
                return false;
            }
        });

    }

    @Test
    public void shouldDeletePreviousAffiliationOfElementOnEquipment(){
        try {
            historyService.registarEquipo(new Equipo(idEquipoCont));
            historyService.registrarElementoConId(new Elemento(idElemCont, TipoElemento.MOUSE, "Lenovo", "Lenovo mouse"));
            historyService.asociarElementoConEquipo(idEquipoCont, idElemCont);
            int buscarElem = idElemCont;
            boolean afiliado = historyService.consultarElemento(idElemCont).getEquipoAsociado() == idEquipoCont;
            idElemCont++;
            historyService.registrarElementoConId(new Elemento(idElemCont, TipoElemento.MOUSE, "ASUS", "ASUS mouse"));
            historyService.asociarElementoConEquipo(idEquipoCont, idElemCont);

            Elemento ultimoElementoAsociado = historyService.consultarElemento(idElemCont);
            Elemento elementoRemovido = historyService.consultarElemento(buscarElem);
            idElemCont++; idEquipoCont++;
            assertTrue(ultimoElementoAsociado.getEquipoAsociado() == idEquipoCont-1 &&
                    elementoRemovido.getEquipoAsociado() == 0 &&
                    afiliado);
        }catch (HistoryServiceException e){
            fail();
        }
    }

    @Test
    public void shouldAffiliateAvailableElementsToEquipment(){
        qt().forAll(Generadores.genElementos()).check(elem ->{
            try {
                historyService.registarEquipo(new Equipo(idEquipoCont));
                historyService.registrarElementoConId(elem);

                historyService.asociarElementoConEquipo(idEquipoCont,idElemCont);
                Elemento elemento = historyService.consultarElemento(idElemCont);

                idEquipoCont++;idElemCont++;
                return !elemento.isDisponible() && elemento.getEquipoAsociado() == idEquipoCont-1;
            } catch (HistoryServiceException e) {

                return false;
            }
        });

    }

}
