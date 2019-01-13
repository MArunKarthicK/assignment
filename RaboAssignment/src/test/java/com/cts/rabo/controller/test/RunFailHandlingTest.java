package com.cts.rabo.controller.test;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/rabotest/failhandling.feature")
public class RunFailHandlingTest {

}
