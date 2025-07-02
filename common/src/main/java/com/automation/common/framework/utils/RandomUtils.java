package com.automation.common.framework.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class RandomUtils {
	private RandomUtils() {
	}

	public static String randomEmail() {
		return "user_" + UUID.randomUUID().toString().substring(0, 6) + "@test.com";
	}

	public static String randomString() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    public static String timestamp() {
        return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
    }
}
