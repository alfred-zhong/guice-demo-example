/*
 * Copyright (c) JIANGSU LNGTOP TECHNOLOGY Co., LTD.
 * 5F Business Center, SAIP, ZETDZ, ZhangJiaGang, Suzhou
 * All rights reserved.
 *
 * "[Description of code or deliverable as appropriate] is the copyrighted,
 * proprietary property of JIANGSU LNGTOP TECHNOLOGY Co., LTD. and its
 * subsidiaries and affiliates which retain all right and title."
 *
 * Revision History
 *
 * Date            Programmer              Notes
 * ---------    ---------------------  --------------------------------------------
 * 2016-05-13      Alfred Zhong            Initial
 *
 */

package com.snowinpluto.demo.providers;

import com.alibaba.druid.pool.DruidDataSource;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import javax.sql.DataSource;

public class DataSourceProvider implements Provider<DataSource> {

    @Inject @Named("jdbc-0.druid.driver-class")
    private String jdbcDriver;
    @Inject @Named("jdbc-0.druid.driver-url")
    private String url;
    @Inject @Named("jdbc-0.user")
    private String username;
    @Inject @Named("jdbc-0.password")
    private String password;

    @Inject @Named("jdbc-0.druid.connection-initial-size")
    private int     initialSize;
    @Inject @Named("jdbc-0.druid.connection-minimum-size")
    private int     minIdle;
    @Inject @Named("jdbc-0.druid.connection-maximum-size")
    private int     maxActive;
    @Inject @Named("jdbc-0.druid.connection-maxwait-time")
    private int     maxWaitTime;
    @Inject @Named("jdbc-0.druid.connection-maxactive-time")
    private int     maxActiveTime;
    @Inject @Named("jdbc-0.druid.connection-minlive-time")
    private int     minLiveTime;
    @Inject @Named("jdbc-0.druid.connection-test-sql")
    private String  testSql;
    @Inject @Named("jdbc-0.druid.pool-prepared-statements")
    private boolean poolPreparedStatements;
    @Inject @Named("jdbc-0.druid.test-while-idle")
    private boolean testWhileIdle;
    @Inject @Named("jdbc-0.druid.test-on-borrow")
    private boolean testOnBorrow;
    @Inject @Named("jdbc-0.druid.test-on-return")
    private boolean testOnReturn;
    @Inject @Named("jdbc-0.druid.config.decrypt")
    private boolean decrypt;

    @Override
    public DataSource get() {
        DruidDataSource dataSource = new DruidDataSource();

        dataSource.setDriverClassName(jdbcDriver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWaitTime);
        dataSource.setTimeBetweenEvictionRunsMillis(maxActiveTime);
        dataSource.setMinEvictableIdleTimeMillis(minLiveTime);
        dataSource.setValidationQuery(testSql);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setConnectionProperties("config.decrypt=" + decrypt);

        return dataSource;
    }
}
