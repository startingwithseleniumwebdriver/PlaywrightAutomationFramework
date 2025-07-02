package com.automation.core.framework.utils;

import java.util.Properties;

import com.automation.core.framework.config.ConfigFactorySingleton;

public final class ConfigUtil {

	private static final Properties properties = ConfigFactorySingleton.getProperties();

	private ConfigUtil() {
	}

	public static String get(String key, String defaultValue) {
		String sysProp = System.getProperty(key);
		if (sysProp != null)
			return sysProp;
		String ownerVal = properties.getProperty(key);
		return ownerVal != null ? ownerVal : defaultValue;
	}

	public static int getInt(String key, int defaultValue) {
	    String raw = get(key, String.valueOf(defaultValue)); // checks system first
	    try {
	        return Integer.parseInt(raw);
	    } catch (NumberFormatException e) {
	        return defaultValue;
	    }
	}

	public static boolean getBoolean(String key, boolean defaultVal) {
	    return Boolean.parseBoolean(get(key, String.valueOf(defaultVal))); // checks system first
	}
}
