package edu.eci.cvds.samples.services;


import com.google.inject.Injector;
import com.google.inject.Guice;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisEquipoDAO;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import edu.eci.cvds.sampleprj.dao.*;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisElementoDAO;
import edu.eci.cvds.samples.services.impl.HistoryServiceImpl;
import edu.eci.cvds.samples.services.impl.HistoryServiceStub;

public class HistoryServicesFactory{

    private static HistoryServicesFactory instance = new HistoryServicesFactory();

    private static Injector injector;

    private static Injector testInjector;

    public HistoryServicesFactory(){

        injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setEnvironmentId("development");
                setClassPathResource("mybatis-config.xml");
                bind(ElementoDAO.class).to(MyBatisElementoDAO.class);
                bind(HistoryService.class).to(HistoryServiceImpl.class);
                bind(EquipoDAO.class).to(MyBatisEquipoDAO.class);
            }
        });

        testInjector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setEnvironmentId("test");
                setClassPathResource("mybatis-config-h2.xml");
                bind(ElementoDAO.class).to(MyBatisElementoDAO.class);
                bind(HistoryService.class).to(HistoryServiceImpl.class);
                bind(EquipoDAO.class).to(MyBatisEquipoDAO.class);
            }
        });

    }

    public HistoryService getHistoryService(){
        return injector.getInstance(HistoryService.class);
    }

    public HistoryService getHistoryServiceForTesting(){
        return testInjector.getInstance(HistoryService.class);
    }

    public static HistoryServicesFactory getInstance(){
        return instance;
    }

}