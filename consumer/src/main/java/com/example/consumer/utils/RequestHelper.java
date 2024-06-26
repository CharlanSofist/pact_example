package com.example.consumer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.core5.http.ContentType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestHelper {

    public List getAll(String url) throws IOException {
        String reponse = Request.get(url)
                .execute().returnContent().asString();
        return getAsList(reponse);
    }

    private List getAsList(String body) throws IOException {
        if (body.isEmpty()) return new ArrayList();
        return new ObjectMapper().readValue(body, ArrayList.class);
    }

    public String post(String url, String body) throws IOException {
        String response = Request.post(url)
                .bodyString(body, ContentType.APPLICATION_JSON)
                .execute().returnContent().asString();
        return response;
    }

    private Map getAsMap(String body) throws JsonMappingException, JsonProcessingException {
        if (body.isEmpty()) return new HashMap();
        return new ObjectMapper().readValue(body, HashMap.class);
    }
}
