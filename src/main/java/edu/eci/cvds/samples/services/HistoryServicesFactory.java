package edu.eci.cvds.samples.services;


import com.google.inject.Injector;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import static com.google.inject.Guice.createInjector;

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
            }
        });

        testInjector = createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setClassPathResource("mybatis-config-h2.xml");
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