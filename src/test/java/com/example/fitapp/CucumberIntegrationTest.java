package com.example.fitapp;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features/",
        glue = { "com.example.fitapp.cucumberglue" })
public class CucumberIntegrationTest {

}
