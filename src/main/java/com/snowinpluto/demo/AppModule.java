package com.snowinpluto.demo;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.linagora.crsh.guice.CrashGuiceConfiguration;
import com.linagora.crsh.guice.CrashGuiceSupport;
import com.snowinpluto.demo.annotation.AddUser;
import com.snowinpluto.demo.interceptor.AddUserInterceptor;
import com.snowinpluto.demo.providers.DataSourceProvider;
import com.snowinpluto.demo.providers.MongoConfigProvider;
import com.snowinpluto.demo.utils.PropertyUtil;
import com.snowinpluto.demo.utils.monogo.MongoConfig;
import org.apache.commons.configuration.Configuration;
import org.crsh.auth.AuthenticationPlugin;
import org.crsh.auth.SimpleAuthenticationPlugin;

import javax.sql.DataSource;

public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        // JDBC 配置绑定
        Configuration jdbcConfig = PropertyUtil.getConfiguration("jdbc.properties");

        bind(String.class).annotatedWith(Names.named("jdbc-0.druid.driver-class")).toInstance(jdbcConfig.getString("jdbc-0.druid.driver-class"));
        bind(String.class).annotatedWith(Names.named("jdbc-0.druid.driver-url")).toInstance(jdbcConfig.getString("jdbc-0.druid.driver-url"));
        bind(String.class).annotatedWith(Names.named("jdbc-0.user")).toInstance(jdbcConfig.getString("jdbc-0.user"));
        bind(String.class).annotatedWith(Names.named("jdbc-0.password")).toInstance(jdbcConfig.getString("jdbc-0.password"));

        bind(Integer.class).annotatedWith(Names.named("jdbc-0.druid.connection-initial-size")).toInstance(jdbcConfig.getInt("jdbc-0.druid.connection-initial-size"));
        bind(Integer.class).annotatedWith(Names.named("jdbc-0.druid.connection-minimum-size")).toInstance(jdbcConfig.getInt("jdbc-0.druid.connection-minimum-size"));
        bind(Integer.class).annotatedWith(Names.named("jdbc-0.druid.connection-maximum-size")).toInstance(jdbcConfig.getInt("jdbc-0.druid.connection-maximum-size"));
        bind(Integer.class).annotatedWith(Names.named("jdbc-0.druid.connection-maxwait-time")).toInstance(jdbcConfig.getInt("jdbc-0.druid.connection-maxwait-time"));
        bind(Integer.class).annotatedWith(Names.named("jdbc-0.druid.connection-maxactive-time")).toInstance(jdbcConfig.getInt("jdbc-0.druid.connection-maxactive-time"));
        bind(Integer.class).annotatedWith(Names.named("jdbc-0.druid.connection-minlive-time")).toInstance(jdbcConfig.getInt("jdbc-0.druid.connection-minlive-time"));
        bind(String.class).annotatedWith(Names.named("jdbc-0.druid.connection-test-sql")).toInstance(jdbcConfig.getString("jdbc-0.druid.connection-test-sql"));
        bind(Boolean.class).annotatedWith(Names.named("jdbc-0.druid.pool-prepared-statements")).toInstance(jdbcConfig.getBoolean("jdbc-0.druid.pool-prepared-statements"));
        bind(Boolean.class).annotatedWith(Names.named("jdbc-0.druid.test-while-idle")).toInstance(jdbcConfig.getBoolean("jdbc-0.druid.test-while-idle"));
        bind(Boolean.class).annotatedWith(Names.named("jdbc-0.druid.test-on-borrow")).toInstance(jdbcConfig.getBoolean("jdbc-0.druid.test-on-borrow"));
        bind(Boolean.class).annotatedWith(Names.named("jdbc-0.druid.test-on-return")).toInstance(jdbcConfig.getBoolean("jdbc-0.druid.test-on-return"));
        bind(Boolean.class).annotatedWith(Names.named("jdbc-0.druid.config.decrypt")).toInstance(jdbcConfig.getBoolean("jdbc-0.druid.config.decrypt"));
        bind(DataSource.class).toProvider(DataSourceProvider.class).in(Singleton.class);

        // Mongo 依赖绑定
        Configuration mongoConfig = PropertyUtil.getConfiguration("mongodb.properties");

        bind(String.class).annotatedWith(Names.named("mongodb.host"))
                          .toInstance(mongoConfig.getString("mongodb.host"));
        bind(Integer.class).annotatedWith(Names.named("mongodb.port"))
                           .toInstance(mongoConfig.getInt("mongodb.port"));
        bind(String.class).annotatedWith(Names.named("mongodb.db"))
                          .toInstance(mongoConfig.getString("mongodb.db"));
        bind(MongoConfig.class).toProvider(MongoConfigProvider.class).in(Singleton.class);

        // 绑定拦截
        bindInterceptor(Matchers.any(), Matchers.annotatedWith(AddUser.class), new AddUserInterceptor());


        // CRaSH
        install(new CrashGuiceSupport());
    }

    @Provides
    public CrashGuiceConfiguration crashConfiguration() {
        return CrashGuiceConfiguration.builder()
                                      .property(AuthenticationPlugin.AUTH.name, "simple")
                                      .property(SimpleAuthenticationPlugin.SIMPLE_USERNAME.name, "admin")
                                      .property(SimpleAuthenticationPlugin.SIMPLE_PASSWORD.name, "123")
                                      .build();
    }
}
