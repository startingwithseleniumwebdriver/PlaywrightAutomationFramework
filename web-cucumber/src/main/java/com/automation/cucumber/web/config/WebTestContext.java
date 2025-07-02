package com.automation.cucumber.web.config;
import com.automation.cucumber.web.context.WebScenarioContext;

public class WebTestContext {
    private WebScenarioContext scenarioContext;

    public void setScenarioContext(WebScenarioContext context) {
        this.scenarioContext = context;
    }

    public WebScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
