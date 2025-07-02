package com.automation.core.framework.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import com.automation.core.framework.config.ConfigFactorySingleton;
import com.automation.core.framework.config.FrameworkConfig;

public final class BrowserConfigResolver {

    private static final FrameworkConfig config = ConfigFactorySingleton.getConfig();
    private static final Properties properties = ConfigFactorySingleton.getProperties();
    private static final String env = config.env().toLowerCase();
    private static final String browser = config.browser().toLowerCase();

    private BrowserConfigResolver() {}

    public static List<String> getBrowserArgs() {
        String key = "browser.args." + browser;
        String value = properties.getProperty(key);
        return value != null && !value.isEmpty()
            ? Arrays.asList(value.split(","))
            : Collections.emptyList();
    }

    public static boolean isTracingEnabled() {
        String key = String.format("browser.tracing.%s.%s", env, browser);
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static boolean isVideoEnabled() {
        String key = String.format("browser.videos.%s.%s", env, browser);
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static boolean isDownloadEnabled() {
        String key = "browser.downloads.enabled." + browser;
        return Boolean.parseBoolean(properties.getProperty(key));
    }

    public static String getTracingDir() {
        return config.tracingDir();
    }
}