package com.automation.core.framework.utils;

import java.lang.reflect.Method;

import org.aeonbits.owner.Converter;

import com.automation.common.framework.enums.ScreenshotMode;

public class EnumConverter implements Converter<ScreenshotMode> {
	public ScreenshotMode convert(Method method, String input) {
		try {
			return ScreenshotMode.valueOf(input.toUpperCase());
		} catch (Exception e) {
			return ScreenshotMode.FAILURE_ONLY;
		}
	}
}