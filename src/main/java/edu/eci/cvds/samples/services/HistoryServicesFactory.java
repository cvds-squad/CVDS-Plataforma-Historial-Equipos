package edu.eci.cvds.samples.services;


import com.google.inject.Injector;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;
import edu.eci.cvds.sampleprj.dao.*;
import static com.google.inject.Guice.createInjector;
import edu.eci.cvds.sampleprj.dao.mybatis.MyBatisElementoDAO;
import edu.eci.cvds.samples.services.impl.HistoryServiceImpl;
import edu.eci.cvds.samples.services.impl.HistoryServiceStub;

class HistoryServicesFactory{

    private static HistoryServicesFactory instance = new HistoryServicesFactory();

    private static Injector injector;

    private static Injector testInjector;

    public HistoryServicesFactory(){

        injector = createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setClassPathResource("mybatis-config.xml");
                bind(ElementoDAO.class).to(MyBatisElementoDAO.class);
                bind(HistoryService.class).to(HistoryServiceImpl.class);
            }
        });

        testInjector = createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setClassPathResource("mybatis-config-h2.xml");
                bind(ElementoDAO.class).to(MyBatisElementoDAO.class);
                bind(HistoryService.class).to(HistoryServiceStub.class);
            }
        });

    }

    public HistoryService getHistoryService(){
        return injector.getInstance(HistoryService.class);
    }

    public HistoryService getHistoryServieForTesting(){
        return injector.getInstance(HistoryService.class);
    }

    public static HistoryServicesFactory getInstance(){
        return instance;
    }

}