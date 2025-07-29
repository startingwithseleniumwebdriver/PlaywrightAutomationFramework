package com.automation.core.framework.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.aeonbits.owner.ConfigFactory;

import com.automation.common.framework.exceptions.FrameworkException;

/*
 * ConfigFactorySingleton.java
 * 
 * This class provides a singleton instance of FrameworkConfig and Properties,
 * ensuring thread-safe access and lazy initialization.
 * 
 * It loads configuration from 'framework.properties' file located in the resources directory.
 */
public final class ConfigFactorySingleton {

    private static FrameworkConfig config;
    private static Properties properties;

    private static final Object LOCK = new Object();

    private ConfigFactorySingleton() {}

    public static FrameworkConfig getConfig() {
        if (config == null) {
            synchronized (LOCK) {
                if (config == null) {
                    load(); // Loads both config and properties
                }
            }
        }
        return config;
    }

    public static Properties getProperties() {
        if (properties == null) {
            synchronized (LOCK) {
                if (properties == null) {
                    if (config == null) {
                        load();
                    } else {
                        properties = ConfigFactory.getProperties();
                    }
                }
            }
        }
        return properties;
    }

    public static synchronized void reload() {
        load(); // Reinitialize both safely
    }

    private static void load() {
    	Properties props = new Properties();
    	try (InputStream is = new FileInputStream("core/src/main/resources/framework.properties")) {
    	    props.load(is);
    	} catch (IOException e) {
    	    throw new FrameworkException("Failed to load framework.properties", e);
    	}
        config = ConfigFactory.create(FrameworkConfig.class);
        ConfigFactory.setProperties(props);
        properties = ConfigFactory.getProperties();
    }
}


