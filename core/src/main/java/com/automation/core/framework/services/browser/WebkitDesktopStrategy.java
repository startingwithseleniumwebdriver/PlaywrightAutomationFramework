package com.automation.core.framework.services.browser;

import java.nio.file.Path;
import java.util.List;

import com.automation.common.framework.enums.BrowserTypeEnum;
import com.automation.core.framework.config.FrameworkConfig;
import com.automation.core.framework.driver.PlaywrightManager;
import com.automation.core.framework.factory.BrowserFactory;
import com.automation.core.framework.utils.BrowserConfigResolver;
import com.automation.core.framework.utils.TraceManager;
import com.automation.core.framework.utils.VideoHelper;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Browser.NewContextOptions;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class WebkitDesktopStrategy implements BrowserLaunchStrategy {

	@Override
	public void launch(Playwright playwright, FrameworkConfig config) {
		List<String> args = BrowserConfigResolver.getBrowserArgs();
        BrowserTypeEnum browserEnum = BrowserTypeEnum.fromString(config.browser());
        BrowserType browserType = BrowserFactory.getBrowserType(playwright, browserEnum);
        
		LaunchOptions launchOptions = new BrowserType.LaunchOptions().setHeadless(config.headless()).setArgs(args);
		Browser browser = browserType.launch(launchOptions);
		PlaywrightManager.setBrowser(browser);

		NewContextOptions contextOptions = new NewContextOptions();

		if (BrowserConfigResolver.isDownloadEnabled()) {
			contextOptions.setAcceptDownloads(true);
		}

		if (BrowserConfigResolver.isVideoEnabled()) {
			Path videoPath = VideoHelper.createVideoDir();
			contextOptions.setRecordVideoDir(videoPath);
		}

		BrowserContext context = browser.newContext(contextOptions);
		PlaywrightManager.setContext(context);

		TraceManager.startTracing();

		Page page = context.newPage();
		PlaywrightManager.setPage(page);
	}
}
