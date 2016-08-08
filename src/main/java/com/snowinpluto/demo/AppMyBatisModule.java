package com.snowinpluto.demo;

import com.snowinpluto.demo.mapper.UserMapper;
import com.snowinpluto.demo.providers.DataSourceProvider;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.mybatis.guice.MyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

public class AppMyBatisModule extends MyBatisModule {
    @Override
    protected void initialize() {
        install(JdbcHelper.MySQL);

        environmentId("gaspipe-query");
        bindDataSourceProviderType(DataSourceProvider.class);
        bindTransactionFactoryType(JdbcTransactionFactory.class);

        addMapperClass(UserMapper.class);
    }
}
