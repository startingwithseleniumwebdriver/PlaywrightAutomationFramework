package com.automation.core.framework.utils;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.automation.core.framework.config.ConfigFactorySingleton;

public class PathResolver {

    private PathResolver() {}

    public static Path resolveLogPath(String testId, long threadId) {
        String dir = System.getProperty("log.dir", "target/logs");
        return Paths.get(System.getProperty("user.dir"),dir, FileNameSanitizer.sanitize(testId) + "-" + threadId + ".log");
    }

    public static Path resolveTracePath(String testId) {
        String dir = ConfigFactorySingleton.getConfig().tracingDir();
        return Paths.get(System.getProperty("user.dir"),dir, FileNameSanitizer.sanitize(testId) + ".zip");
    }

    public static Path resolveScreenshotPath(String testId) {
        String dir = ConfigFactorySingleton.getConfig().screenshotsDir();
        return Paths.get(System.getProperty("user.dir"),dir, FileNameSanitizer.sanitize(testId), FileNameSanitizer.sanitize(testId) + ".png");
    }

    public static Path resolveVideoDir(String testId) {
        String dir = ConfigFactorySingleton.getConfig().videosDir();
        return Paths.get(System.getProperty("user.dir"),dir, FileNameSanitizer.sanitize(testId));
    }
}

