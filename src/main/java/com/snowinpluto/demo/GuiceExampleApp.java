package com.snowinpluto.demo;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.snowinpluto.demo.utils.PropertyUtil;
import org.apache.commons.configuration.Configuration;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Hello world!
 */
public class GuiceExampleApp {
    private static final Logger log = LoggerFactory.getLogger(GuiceExampleApp.class);

    public static Injector injector;

    public static void main(String[] args) {
        injector = Guice.createInjector(new AppModule(),
                                        new AppServletModule(),
                                        new AppMyBatisModule());

        // Jetty 线程
        final Thread jettyThread = new Thread() {
            @Override
            public void run() {
                try {
                    // 读取服务器配置
                    Configuration appConfig = PropertyUtil.getConfiguration("etc/server.properties");

                    // 配置服务器
                    Server server = new Server(appConfig.getInt("server.port"));

                    ServletContextHandler handler =
                            new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
                    handler.addFilter(GuiceFilter.class, "/*", EnumSet.allOf(DispatcherType.class));
                    handler.addServlet(DefaultServlet.class, "/");

                    // 启动服务器
                    server.start();
                    server.join();
                } catch (Exception e) {
                    log.error("Jetty Server failed to start", e);
                }
            }
        };

        // 启动线程
        jettyThread.start();
    }
}
