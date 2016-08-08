package com.snowinpluto.demo;

import com.google.inject.Injector;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class AppGuiceContainer extends GuiceContainer {

    @Inject
    public AppGuiceContainer(Injector injector) {
        super(injector);
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //you can customize the behavior here.
        super.service(request, response);
    }
}