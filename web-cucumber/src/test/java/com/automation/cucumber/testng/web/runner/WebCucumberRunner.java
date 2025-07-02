package com.automation.cucumber.testng.web.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/features",
    glue = {
        "com.automation.cucumber.web.steps",
        "com.automation.cucumber.web.hooks"
    },
    plugin = {
        "pretty",
        "html:target/web-cucumber-report.html",
        "json:target/web-cucumber-report.json"
    },
    monochrome = true
)
public class WebCucumberRunner extends AbstractTestNGCucumberTests {
	
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
