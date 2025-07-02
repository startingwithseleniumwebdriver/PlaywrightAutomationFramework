package com.automation.core.framework.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.automation.core.framework.logging.LoggerManager;

import io.qameta.allure.Allure;

public class VideoHelper {
	private VideoHelper() {}

	public static Path createVideoDir() {
		Path videoPath = PathResolver.resolveVideoDir(ThreadSafeContext.getTestId());
		try {
			Files.createDirectories(videoPath);
		} catch (IOException e) {
			LoggerManager.getLogger().warn("Failed to create video folder", e);
		}
		return videoPath;
	}
	

	/**
	 * Attach video to Allure report after test.
	 */
	public static void attachVideoIfExists() {
		Path videoDir = Paths.get("target", "videos", FileNameSanitizer.sanitize(ThreadSafeContext.getTestId()));
		if (!Files.exists(videoDir) || !Files.isDirectory(videoDir)) return;

		try {
			Files.list(videoDir)
				.filter(path -> path.toString().endsWith(".webm") || path.toString().endsWith(".mp4"))
				.forEach(VideoHelper::attachToAllure);
		} catch (IOException e) {
			LoggerManager.getLogger().warn("Error reading video directory", e);
		}
	}

	private static void attachToAllure(Path videoPath) {
		try (InputStream is = Files.newInputStream(videoPath)) {
			Allure.addAttachment("Test Video", "video/webm", is, ".webm");
			LoggerManager.getLogger().info("Video attached to Allure: {}", videoPath);
		} catch (IOException e) {
			LoggerManager.getLogger().warn("Failed to attach video: {}", videoPath, e);
		}
	}
}