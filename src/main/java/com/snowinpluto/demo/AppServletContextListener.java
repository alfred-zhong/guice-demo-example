package com.snowinpluto.demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class AppServletContextListener extends GuiceServletContextListener {

    public static Injector injector = null;

    @Override
    protected Injector getInjector() {
        injector = Guice.createInjector(new AppModule(),
                                        new AppServletModule(),
                                        new AppMyBatisModule(),
                                        new AppMvcModule());

        return injector;
    }
}
