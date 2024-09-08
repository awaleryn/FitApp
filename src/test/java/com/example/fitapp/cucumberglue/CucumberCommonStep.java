package com.example.fitapp.cucumberglue;

import com.example.fitapp.utils.TestUtils;
import io.cucumber.java.en.Then;
import org.springframework.test.web.servlet.MvcResult;

public class CucumberCommonStep {


    private MvcResult result;

    public void setResult(MvcResult result) {
        this.result = result;
    }

    @Then("the response status should be {int}")
    public void theResponseStatusShouldBe(int status) {
        TestUtils.assertResponseStatus(result.getResponse().getStatus(), status);
    }
}
