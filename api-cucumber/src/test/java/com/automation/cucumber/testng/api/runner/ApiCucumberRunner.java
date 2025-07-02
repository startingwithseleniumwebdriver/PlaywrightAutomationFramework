package com.automation.cucumber.testng.api.runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
	    features = "src/test/resources/features",
	    glue = {
	        "com.automation.cucumber.api.steps",
	        "com.automation.cucumber.api.hooks"
	    },
	    plugin = {
	        "pretty",
	        "html:target/api-cucumber-report.html",
	        "json:target/api-cucumber-report.json"
	    },
	    monochrome = true
	)
	public class ApiCucumberRunner extends AbstractTestNGCucumberTests {

	    @Override
	    @DataProvider(parallel = true)
	    public Object[][] scenarios() {
	        return super.scenarios();
	    }
	}

