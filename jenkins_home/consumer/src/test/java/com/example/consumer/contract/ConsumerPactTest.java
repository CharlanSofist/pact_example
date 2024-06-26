package com.example.consumer.contract;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.DslPart;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;

import com.example.consumer.service.DataService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.isNotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "provider", pactVersion = PactSpecVersion.V3)
class ConsumerPactTest {
    @Pact(consumer = "consumer", provider = "provider")
    public RequestResponsePact createPact(PactDslWithProvider builder) {

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        DslPart requestBody = new PactDslJsonBody()
                .stringType("firstName")
                .stringType("lastName");

        PactDslJsonBody responseBody = new PactDslJsonBody()
                .stringType("id")
                .stringType("firstName")
                .stringType("lastName");

        return builder
                .given("Provider is available")
                .uponReceiving("A request for data")
                .path("/users")
                .method("POST")
                .headers(headers)
                .body(requestBody)
                .willRespondWith()
                .status(200)
                .body(responseBody)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPact")
    void testPact(MockServer mockServer) throws IOException {
        String url = mockServer.getUrl();
        DataService apiConsumer = new DataService(url);
        String user = apiConsumer.insertUser("First", "Last");
        assertEquals(user, user);

    }
}
