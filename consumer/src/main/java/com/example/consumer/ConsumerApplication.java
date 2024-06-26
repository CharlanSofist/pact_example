package com.example.consumer;

import com.example.consumer.service.DataService;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public class ConsumerApplication {
    static DataService apiConsumer = new DataService("http://localhost:8080");

    public static void main(String[] args) throws IOException {
        String result = apiConsumer.insertUser("Charlan", "Bettiol");
        System.out.println("ID CADASTRADO: " + result);

        List<Map> accounts = apiConsumer.getUsers();
        for(Map acc: accounts) {
            System.out.println("Resposta GET: " + acc.get("id") + " " + acc.get("lastName"));
        }
    }

}
