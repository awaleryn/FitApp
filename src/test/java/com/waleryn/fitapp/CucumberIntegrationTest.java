package com.waleryn.fitapp;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/",
        glue = { "com.waleryn.fitapp.cucumberglue" })
public class CucumberIntegrationTest {

}
