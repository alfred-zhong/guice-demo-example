package com.snowinpluto.demo.controller;

import com.google.inject.servlet.SessionScoped;
import org.zdevra.guice.mvc.ModelMap;
import org.zdevra.guice.mvc.annotations.Controller;
import org.zdevra.guice.mvc.annotations.Path;
import org.zdevra.guice.mvc.freemarker.annotations.FreemarkerView;

@Controller
@SessionScoped
@FreemarkerView("user.ftl")
public class UserController {

    @Path("/*")
    public void go(ModelMap modelMap) {
        modelMap.put("nowName", "Jack");
    }
}
