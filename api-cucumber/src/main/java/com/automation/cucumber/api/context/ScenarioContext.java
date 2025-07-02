package com.automation.cucumber.api.context;


import org.apache.logging.log4j.Logger;

import com.microsoft.playwright.APIRequestContext;

public class ScenarioContext {
    private final APIRequestContext requestContext;
    private final Logger logger;

    public ScenarioContext(APIRequestContext requestContext, Logger logger) {
        this.requestContext = requestContext;
        this.logger = logger;
    }

    public APIRequestContext getRequestContext() {
        return requestContext;
    }

    public Logger getLogger() {
        return logger;
    }
}
