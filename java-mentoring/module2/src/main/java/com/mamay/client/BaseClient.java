package com.mamay.client;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public abstract class BaseClient {

    private static final String BASE_URI = "http://127.0.0.1:8080/module2task1";

    protected WebTarget webTarget;

    public BaseClient() {
        ClientConfig config = new ClientConfig();
        config.register(LoggingFilter.class);
        Client client = ClientBuilder.newClient(config);
        webTarget = client.target(BASE_URI);
    }

    public abstract void run();
}
