package com.automation.core.framework.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.automation.common.framework.enums.ScreenshotMode;
import com.automation.core.framework.config.ConfigFactorySingleton;
import com.automation.core.framework.driver.PlaywrightManager;
import com.automation.core.framework.logging.LoggerManager;
import com.microsoft.playwright.Page;

public class ScreenshotHelper {
	private static final ScreenshotMode mode = ScreenshotMode.from(ConfigFactorySingleton.getConfig().screenshotMode());

	private ScreenshotHelper() {}

	public static Path captureScreenshot(boolean testFailed) {
		if (mode == ScreenshotMode.NONE) return null;
		if (mode == ScreenshotMode.FAILURE_ONLY && !testFailed) return null;

		try {
			Page page = PlaywrightManager.getPage();
			if (page == null) return null;
			
			Path dir = PathResolver.resolveScreenshotPath(ThreadSafeContext.getTestId());
			Files.createDirectories(dir.getParent());

			page.screenshot(new Page.ScreenshotOptions()
					.setPath(dir)
					.setFullPage(ConfigFactorySingleton.getConfig().screenshotFullPage()));

			LoggerManager.getLogger().info("Screenshot saved: {}", dir);
			return dir;
		} catch (IOException e) {
			LoggerManager.getLogger().error("Failed to capture screenshot", e);
			return null;
		}
	}
}