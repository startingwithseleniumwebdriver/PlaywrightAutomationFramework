package com.automation.core.framework.utils;

public class ThreadSafeContext {
	private static final ThreadLocal<String> testId = new ThreadLocal<>();

	public static void setTestId(String id) {
		testId.set(id);
	}

	public static String getTestId() {
		return testId.get();
	}

	public static void clear() {
		testId.remove();
	}
}
