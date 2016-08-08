package com.snowinpluto.demo.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Properties 配置文件
 */
public class PropertyUtil {

    /**
     * 初始化配置文件
     * @param fileName 文件路径
     * @return Configuration
     */
    public static Configuration getConfiguration(String fileName) {
        Configuration configuration = null;
        try {
            configuration = new PropertiesConfiguration(checkNotNull(fileName));
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
        return configuration;
    }
}
