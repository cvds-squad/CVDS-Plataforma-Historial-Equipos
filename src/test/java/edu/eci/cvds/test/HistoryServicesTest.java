package edu.eci.cvds.test;

import com.google.inject.Inject;
import edu.eci.cvds.samples.entities.Elemento;
import edu.eci.cvds.samples.services.HistoryService;
import edu.eci.cvds.samples.services.HistoryServiceException;
import edu.eci.cvds.samples.services.HistoryServicesFactory;
import org.apache.ibatis.session.SqlSession;
//import org.junit.After;
import org.junit.Test;

//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;

import static org.quicktheories.QuickTheory.qt;

public class HistoryServicesTest {

    @Inject
    private SqlSession sqlSession;

    private HistoryService historyService;

    private int idElemCont;

    public HistoryServicesTest(){
        historyService = HistoryServicesFactory.getInstance().getHistoryServiceForTesting();
        idElemCont = 1;
    }

    @Test
    public void shouldInsertAndConsultNewElements(){
        idElemCont = 1;
        qt().forAll(Generadores.genElementos()).check(elem ->{
            try{
                historyService.registrarElemento(elem);

                Elemento elemInserted = historyService.consultarElemento(idElemCont++);

                return  elem.getDescripcion().equals(elemInserted.getDescripcion()) &&
                        elem.getMarca().equals(elemInserted.getMarca()) &&
                        elem.getTipelement().equals(elemInserted.getTipelement());
            }catch (HistoryServiceException e){
                e.printStackTrace();
                return false;
            }
        });
    }




    /*
    @After
    public void clearDB() throws SQLException,ClassNotFoundException {
        System.out.println("Borrando");

        String url = "jdbc:h2:file:./target/db/testdb;MODE=PostgreSQL";
        String driver = "org.h2.Driver";
        String username = "sa";
        String password = "";

        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url,username,password);
        connection.setAutoCommit(false);
        Statement statement = connection.createStatement();
        statement.execute("delete from elementos");
        connection.commit();
    }
    */

}
