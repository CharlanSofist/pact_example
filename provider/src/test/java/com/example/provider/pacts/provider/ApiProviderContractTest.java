package com.example.provider.pacts.provider;

import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

import com.example.provider.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;

import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.IgnoreNoPactsToVerify;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.State;

@Provider("provider")
@IgnoreNoPactsToVerify(ignoreIoErrors = "true")
@PactFolder("src/test/resources/pact/")
public class ApiProviderContractTest {

    private static WireMockServer mockServer;

    @BeforeEach
    void setUp(PactVerificationContext context) {
        mockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8080));
        mockServer.start();

        if (context != null) {
            context.setTarget(new HttpTestTarget("127.0.0.1", 8080, "/"));
        } else {
            assumerNoPacts();

        }

    }

    @AfterEach
    public void stop() {
        mockServer.stop();
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pacTestTemplate(PactVerificationContext context) {
        if (context != null) {
            context.verifyInteraction();
        } else {
            assumerNoPacts();
        }

    }

    @State("Provider is available")
    void requestCategoriesObject() throws Exception {
        String responseJSON = generateJSONResponse();

        mockServer.stubFor(WireMock.post(WireMock.urlEqualTo("/users"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(responseJSON)));
    }

    private String generateJSONResponse() throws JsonProcessingException {
        User user = new User("1", "Jhon", "Doe");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }

    private void assumerNoPacts() {
        String errorMessage = "Verfique se existem contratos para o Provedor.";
        System.out.println("\u001B[33m" + errorMessage + "\u001B[0m");
        Assumptions.assumeTrue(false, errorMessage);
    }

}
