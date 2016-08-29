package com.snowinpluto.demo;

import org.mybatis.guice.XMLMyBatisModule;

public class AppMyBatisModule extends XMLMyBatisModule {

    @Override
    protected void initialize() {
        setEnvironmentId("test");
        setClassPathResource("mybatis-config.xml");
    }
}
