package com.automation.core.framework.base;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.automation.core.framework.driver.PlaywrightManager;
import com.automation.core.framework.logging.LoggerManager;
import com.automation.core.framework.services.BrowserService;
import com.automation.core.framework.utils.AllureAttachmentUtil;
import com.automation.core.framework.utils.ArtifactZipper;
import com.automation.core.framework.utils.ConfigHelper;
import com.automation.core.framework.utils.ScreenshotHelper;
import com.automation.core.framework.utils.ThreadSafeContext;
import com.automation.core.framework.utils.TraceManager;
import com.automation.core.framework.utils.VideoHelper;
import com.microsoft.playwright.Page;

import io.qameta.allure.Allure;

public class BaseWebTest {

	protected Logger log;
	protected Page page;
	private final BrowserService browserService = new BrowserService();

	@BeforeMethod(alwaysRun = true)
	@Parameters({"device.profile", "browser"})
	public void setUp(@Optional String deviceProfile, @Optional String browser, Method method) {
	    if (deviceProfile != null) System.setProperty("device.profile", deviceProfile);
	    if (browser != null) System.setProperty("browser", browser);
	    String testId = method.getName();
	    ThreadSafeContext.setTestId(testId);
	    log = LoggerManager.getLogger();
	    log.info("Web Test Started");

		browserService.launchBrowser();
		page = PlaywrightManager.getPage();
		TraceManager.startTracing();
		page.navigate(ConfigHelper.getWebUrl());
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {
	    boolean testFailed = result.getStatus() == ITestResult.FAILURE;

	    // Capture screenshot and attach to Allure if failed
	    Path screenshotPath = ScreenshotHelper.captureScreenshot(testFailed);
	    if (testFailed && screenshotPath != null) {
	        try {
	            byte[] screenshot = Files.readAllBytes(screenshotPath);
	            Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
	        } catch (IOException e) {
	            log.warn("Failed to attach screenshot to Allure", e);
	        }
	    }

	    // Trace
	    TraceManager.stopTracing();

	    // Video (if exists)
	    VideoHelper.attachVideoIfExists();

	    // Log
	    AllureAttachmentUtil.attachLogToAllure("Test Log - " + result.getName(), LoggerManager.getLogFilePath());

	    // Zip Artifacts
	    ArtifactZipper.zipAndAttachArtifacts();

	    // Clean up
	    browserService.close();
	    LoggerManager.removeLogger();
	    ThreadSafeContext.clear();
	}

}
