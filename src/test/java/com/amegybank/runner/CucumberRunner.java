package com.amegybank.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by narottamgla@gmail.com on 2/11/19.
 */
@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "io.qameta.allure.cucumber3jvm.AllureCucumber3Jvm", "pretty", "json:target/cucumber-report/report.json" },

   features = { "src/test/resources/features/" }, glue = { "com.amegybank.steps" })
public class CucumberRunner
{}
