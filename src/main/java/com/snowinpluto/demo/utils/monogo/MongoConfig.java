package com.snowinpluto.demo.utils.monogo;

import com.google.common.base.Objects;

/**
 * Mongo DB 配置
 */
public class MongoConfig {

    // MongoDB 地址
    private String host;

    // MongoDB 端口
    private int port;

    public MongoConfig(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public MongoConfig setHost(String host) {
        this.host = host;
        return this;
    }

    public int getPort() {
        return port;
    }

    public MongoConfig setPort(int port) {
        this.port = port;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MongoConfig that = (MongoConfig) o;
        return port == that.port &&
                Objects.equal(host, that.host);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(host, port);
    }
}
