package com.automation.cucumber.testng.web.runner;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.core.framework.listners.TaggedRetryAnalyzer;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;

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
public class WebCucumberTaggedReTryAnalyzerRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @Test(dataProvider = "scenarios", retryAnalyzer = TaggedRetryAnalyzer.class)
    public void runScenario(PickleWrapper pickle, FeatureWrapper feature) {
        super.runScenario(pickle, feature);
    }
}
