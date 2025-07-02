package com.automation.cucumber.api.config;

import com.automation.cucumber.api.context.ScenarioContext;

public class TestContext {
    private ScenarioContext scenarioContext;

    public void setScenarioContext(ScenarioContext context) {
        this.scenarioContext = context;
    }

    public ScenarioContext getScenarioContext() {
        return scenarioContext;
    }
}
