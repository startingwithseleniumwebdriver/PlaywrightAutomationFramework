package com.automation.core.framework.services;

import com.automation.common.framework.enums.BrowserTypeEnum;
import com.automation.common.framework.enums.DeviceProfileEnum;
import com.automation.core.framework.config.ConfigFactorySingleton;
import com.automation.core.framework.config.FrameworkConfig;
import com.automation.core.framework.driver.PlaywrightManager;
import com.automation.core.framework.logging.LoggerManager;
import com.automation.core.framework.services.browser.BrowserLaunchStrategy;
import com.automation.core.framework.services.browser.BrowserLauncherFactory;
import com.automation.core.framework.utils.BrowserLoggerUtil;
import com.microsoft.playwright.Playwright;

public class BrowserService {

	private final FrameworkConfig config = ConfigFactorySingleton.getConfig();

	public void launchBrowser() {
		Playwright playwright = Playwright.create();
		PlaywrightManager.setPlaywright(playwright);

		BrowserTypeEnum browser = BrowserTypeEnum.fromString(config.browser());
		DeviceProfileEnum profile = DeviceProfileEnum.fromString(config.deviceProfile());

		BrowserLaunchStrategy strategy = BrowserLauncherFactory.getStrategy(browser, profile);
		BrowserLoggerUtil.logBrowserSummary();
		strategy.launch(PlaywrightManager.getPlaywright(), config);
	}

	public void close() {
		LoggerManager.getLogger().info("Closing browser...");
		PlaywrightManager.close();
	}
}
