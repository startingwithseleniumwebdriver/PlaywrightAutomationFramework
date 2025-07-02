package com.automation.common.framework.enums;

public enum ScreenshotMode {
	ALL, FAILURE_ONLY, NONE;

	public static ScreenshotMode from(String value) {
		try {
			return ScreenshotMode.valueOf(value.toUpperCase());
		} catch (Exception e) {
			return FAILURE_ONLY;
		}
	}
}
