package com.automation.cucumber.testng.api.runner;

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
        "com.automation.cucumber.api.steps",
        "com.automation.cucumber.api.hooks"
    },
    plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "json:target/cucumber-report.json"
    },
    monochrome = true
)
public class ApiCucumberTaggedReTryAnalyzerRunner extends AbstractTestNGCucumberTests {

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