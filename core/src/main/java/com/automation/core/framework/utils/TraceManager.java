package com.automation.core.framework.utils;

import java.nio.file.Path;

import com.automation.core.framework.driver.PlaywrightManager;
import com.automation.core.framework.logging.LoggerManager;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Tracing.StartOptions;
import com.microsoft.playwright.Tracing.StopOptions;

public class TraceManager {

	private static final ThreadLocal<Boolean> tracingStarted = new ThreadLocal<>();

	public static void startTracing() {
		BrowserContext context = PlaywrightManager.getContext();
		if (context != null && tracingStarted.get() == null && BrowserConfigResolver.isTracingEnabled()) {
			context.tracing().start(new StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
			tracingStarted.set(true);
		}
	}

	public static void stopTracing() {
		BrowserContext context = PlaywrightManager.getContext();
		if (context != null && Boolean.TRUE.equals(tracingStarted.get())) {
			Path tracePath = PathResolver.resolveTracePath(ThreadSafeContext.getTestId());
			context.tracing().stop(new StopOptions().setPath(tracePath));
			LoggerManager.getLogger().info("Trace saved: {}", tracePath);
		}
		tracingStarted.remove();
	}
}