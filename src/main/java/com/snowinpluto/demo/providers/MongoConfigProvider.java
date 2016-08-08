package com.snowinpluto.demo.providers;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;
import com.snowinpluto.demo.utils.monogo.MongoConfig;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class MongoConfigProvider implements Provider<MongoConfig> {

    @Inject
    @Named("mongodb.host")
    private String host;

    @Inject
    @Named("mongodb.port")
    private Integer port;

    @Override
    public MongoConfig get() {
        checkNotNull(host);
        checkNotNull(port);
        checkArgument(port > 0);

        return new MongoConfig(host, port);
    }
}
