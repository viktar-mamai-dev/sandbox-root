package com.mamay.main;

import com.mamay.client.BaseClient;
import com.mamay.client.UserClient;

public class AppRunner {

    public static void main(String[] args) {
        BaseClient client1 = new UserClient();
        client1.run();
    }
}
