package com.snowinpluto.demo;

import com.snowinpluto.demo.controller.UserController;
import org.zdevra.guice.mvc.MvcModule;
import org.zdevra.guice.mvc.freemarker.FreemarkerModule;

public class AppMvcModule extends MvcModule {
    @Override
    protected void configureControllers() {
        install(new FreemarkerModule(getServletContext()));

        control("/mvc/user/*").withController(UserController.class);
    }
}
