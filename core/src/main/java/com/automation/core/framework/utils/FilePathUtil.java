package com.automation.core.framework.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FilePathUtil {
	private FilePathUtil() {
	}

	public static Path getTimestampedFolder(String baseFolder, String env, String testId) {
		String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
		return Paths.get(baseFolder, env, testId, timestamp);
	}
}
