package com.automation.cucumber.web.hooks;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.logging.log4j.Logger;

import com.automation.core.framework.driver.PlaywrightManager;
import com.automation.core.framework.logging.LoggerManager;
import com.automation.core.framework.services.BrowserService;
import com.automation.core.framework.utils.AllureAttachmentUtil;
import com.automation.core.framework.utils.ArtifactZipper;
import com.automation.core.framework.utils.ScreenshotHelper;
import com.automation.core.framework.utils.ThreadSafeContext;
import com.automation.core.framework.utils.TraceManager;
import com.automation.core.framework.utils.VideoHelper;
import com.automation.cucumber.web.config.WebTestContext;
import com.automation.cucumber.web.context.WebScenarioContext;
import com.microsoft.playwright.Page;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class WebCucumberHooks {
    private final WebTestContext testContext;
    private final BrowserService browserService = new BrowserService();
    private Page page;
    private Logger logger;

	public WebCucumberHooks(WebTestContext testContext) {
		this.testContext = testContext;
	}

	@Before
	public void beforeScenario(Scenario scenario) {
	    String testName = scenario.getName().replaceAll("\\s+", "_");
	    ThreadSafeContext.setTestId(testName);
	    logger = LoggerManager.getLogger();
	    logger.info("Starting web scenario: {}", testName);
	    
        browserService.launchBrowser();

        page = PlaywrightManager.getPage();
        testContext.setScenarioContext(new WebScenarioContext(page, logger));
        TraceManager.startTracing();

        logger.info("Starting web scenario: {}", scenario.getName());
	}

	@After
	public void afterScenario(Scenario scenario) {
	    boolean failed = scenario.isFailed();
	    String testId = ThreadSafeContext.getTestId();
	    logger.info("Ending scenario: {}", testId);
	    
	    Path screenshotPath = ScreenshotHelper.captureScreenshot(failed);

	    if (failed && screenshotPath != null) {
	        try {
	            byte[] screenshot = Files.readAllBytes(screenshotPath);
	            Allure.addAttachment("Failure Screenshot", new ByteArrayInputStream(screenshot));
	            scenario.attach(screenshot, "image/png", "Failure Screenshot");
	        } catch (IOException e) {
	            logger.warn("Failed to attach screenshot", e);
	        }
	    }

	    // Trace
	    TraceManager.stopTracing();

	    // Video
	    VideoHelper.attachVideoIfExists();
	    
	    AllureAttachmentUtil.attachLogToAllure("Web Log - " + testId, LoggerManager.getLogFilePath());
	    
	    // Zip
	    ArtifactZipper.zipAndAttachArtifacts();

	    // Cleanup
	    browserService.close();
	    LoggerManager.removeLogger();
	    ThreadSafeContext.clear();
	}
}
