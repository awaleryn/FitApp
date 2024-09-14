package com.waleryn.fitapp.cucumberglue;

import com.waleryn.fitapp.auth.AuthenticationRequest;
import com.waleryn.fitapp.auth.AuthenticationResponse;
import com.waleryn.fitapp.auth.LoginRequest;
import com.waleryn.fitapp.auth.RegisterRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CucumberAuthenticationSteps {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    private MvcResult result;
    private String jwtToken;
    private final CucumberCommonStep cucumberCommonStep;


    public CucumberAuthenticationSteps(CucumberCommonStep cucumberCommonStep) {
        this.cucumberCommonStep = cucumberCommonStep;
    }

    @Given("a user with the first name {string}, last name {string}, email {string}, and password {string} registers successfully")
    public void theUserIsRegisteredWith(String firstName, String lastName, String email, String password) throws Exception {
        RegisterRequest registerRequest = new RegisterRequest(firstName, lastName, email, password);

        String requestBody = objectMapper.writeValueAsString(registerRequest);

        result = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
        cucumberCommonStep.setResult(result);
        setJwtTokenFromResponse();
    }

    @When("the user tries to log in with email {string} and password {string}")
    public void theUserIsAbleToLoginWith(String email, String password) throws Exception {
        LoginRequest loginRequest = new LoginRequest(email, password);

        String requestBody = objectMapper.writeValueAsString(loginRequest);

        result = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn();
        cucumberCommonStep.setResult(result);

    }

    @When("the user tries to authenticate with email {string} and password {string}")
    public void theUserIsAbleToAuthenticateWith(String email, String password) throws Exception {
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(email, password);

        String requestBody = objectMapper.writeValueAsString(authenticationRequest);

        result = mockMvc.perform(post("/api/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andReturn();
        cucumberCommonStep.setResult(result);
        setJwtTokenFromResponse();
    }

    @When("the user logs out")
    public void theUserIsAbleToLoginWith() throws Exception {

        result = mockMvc.perform(post("/api/logout")
                        .header("Authorization", "Bearer " + jwtToken))
                .andReturn();
        cucumberCommonStep.setResult(result);
    }

    @When("the user wants to refresh token")
    public void theUserIsAbleToRefreshToken() throws Exception {

        result = mockMvc.perform(post("/api/auth/refresh-token")
                        .header("Authorization", "Bearer " + jwtToken))
                .andReturn();
        cucumberCommonStep.setResult(result);
    }

    private void setJwtTokenFromResponse() throws UnsupportedEncodingException, JsonProcessingException {
        String jsonResponse = result.getResponse().getContentAsString();
        AuthenticationResponse authenticationResponse = objectMapper.readValue(jsonResponse, AuthenticationResponse.class);
        jwtToken = authenticationResponse.getAccessToken();
    }
}
