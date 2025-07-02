package com.automation.common.framework.utils;

import com.automation.common.framework.exceptions.FrameworkException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	private static final ObjectMapper mapper = new ObjectMapper();

	private JsonUtils() {
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (Exception e) {
			throw new FrameworkException("Failed to deserialize JSON", e);
		}
	}

	public static String toJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new FrameworkException("Failed to serialize object", e);
		}
	}
}
