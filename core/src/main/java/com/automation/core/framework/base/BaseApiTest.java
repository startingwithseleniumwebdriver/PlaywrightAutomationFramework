package com.automation.core.framework.base;

import java.lang.reflect.Method;

import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.automation.core.framework.api.client.ApiClient;
import com.automation.core.framework.logging.LoggerManager;
import com.automation.core.framework.utils.AllureAttachmentUtil;
import com.automation.core.framework.utils.ThreadSafeContext;

public class BaseApiTest {
    protected Logger log;

    @BeforeMethod(alwaysRun = true)
    public void setupLoggerAndApi(Method method) {
	    String testId = method.getName();
	    ThreadSafeContext.setTestId(testId);
        log = LoggerManager.getLogger();
        log.info("API Test Started");
        ApiClient.init();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownLoggerAndApi(ITestResult result) {
    	log.info("Tearing down API context. Ending test: {}", this.getClass().getSimpleName());
        ApiClient.close();

        AllureAttachmentUtil.attachLogToAllure("API Log - " + result.getName(), LoggerManager.getLogFilePath());
        LoggerManager.removeLogger();
        ThreadSafeContext.clear();
    }
}
