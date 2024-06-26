package com.example.consumer;

import com.example.consumer.service.DataService;
import com.example.consumer.utils.RequestHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DataServiceTest {

    private DataService dataService;

    @Mock
    private RequestHelper requestHelperMock;

    private final String baseUrl = "http://localhost:8080/api"; // Exemplo de URL base para os testes

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        dataService = new DataService(baseUrl);
        dataService.helper = requestHelperMock;
    }

        @Test
    public void testInsertUser() throws IOException {
        // Mockando o retorno do RequestHelper para insertUser
        String expectedResponse = "{\"message\": \"User inserted successfully\"}";
        when(requestHelperMock.post(anyString(), anyString()))
                .thenReturn(expectedResponse);

        // Chamando o método insertUser e verificando o retorno
        String firstName = "John";
        String lastName = "Doe";
        String actualResponse = dataService.insertUser(firstName, lastName);

        assertEquals(expectedResponse, actualResponse);

        // Verificando se o método System.out.println foi chamado com o response mockado
        verify(requestHelperMock).post(baseUrl + "/users", "{\"firstName\": \"John\", \"lastName\": \"Doe\"}");
        System.out.println(actualResponse);
    }
}
