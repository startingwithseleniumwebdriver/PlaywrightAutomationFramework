package com.automation.common.framework.enums;

public enum DeviceProfileEnum {
	DESKTOP, MOBILE;

	public static DeviceProfileEnum fromString(String value) {
		try {
			return DeviceProfileEnum.valueOf(value.toUpperCase());
		} catch (IllegalArgumentException e) {
			return DESKTOP;
		}
	}
}
