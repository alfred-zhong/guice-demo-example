package com.snowinpluto.demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class AppServletContextListener extends GuiceServletContextListener {

    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new AppModule(),
                                    new AppServletModule(),
                                    new AppMyBatisModule(),
                                    new AppMvcModule());
    }
}
