package com.example.consumer.service;

import com.example.consumer.utils.RequestHelper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DataService {

    public RequestHelper helper = new RequestHelper();

    private final String baseUrl;

    public DataService(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    public List<Map> getUsers() throws IOException {
        List<Map> resp = helper.getAll(baseUrl + "/users");
        for(Map account: resp) {
            System.out.println(String.format("%s - %s", account.get("id"), account.get("nome")));
        }
        return resp;
    }

    public String insertUser(String firstName, String lastName) throws IOException {
        String resp = helper.post(baseUrl + "/users", String.format("{\"firstName\": \"%s\", \"lastName\": \"%s\"}", firstName, lastName));
        System.out.println(resp);
        return resp;
    }
}