package com.mamay.client;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mamay.entity.User;

public class UserClient extends BaseClient {

    public UserClient() {
        webTarget = webTarget.path("/users");
    }

    public void run() {
        testRetrieveAll();

        User user = new User("Mark", "Twen", "bigmark12", "mark.twain@para.com");
        Long userId = testCreate(user);

        testUpdate(userId);
        testRetrieve(userId);

        ImageClient imageClient = new ImageClient(userId);
        imageClient.run();

        testDelete(userId);
        testRetrieve(userId);

    }

    private void testRetrieveAll() {
        Response response = webTarget.path("").request().accept(MediaType.WILDCARD).get();
        System.out.println("Testing GET /users. Status= " + response.getStatus());
    }

    private void testRetrieve(Long userId) {
        Response response = webTarget.path("/" + userId).request().accept(MediaType.MEDIA_TYPE_WILDCARD).get();
        System.out.printf("Testing GET /users/%d. Status=%s%n", userId, response.getStatus());
        System.out.println("\tEntity= " + response.readEntity(User.class));
    }

    private Long testCreate(User user) {
        Response response = webTarget.path("").request(MediaType.APPLICATION_XML).post(Entity.entity(user, MediaType.APPLICATION_XML));
        System.out.println("Testing POST /users. Status= " + response.getStatus());
        User createdUser = response.readEntity(User.class);
        System.out.println("\tEntity= " + createdUser);
        return createdUser.getId();
    }

    private void testUpdate(Long userId) {
        User user = new User("Aizek", "Azimov", "aizekmegarobot", "aizek.azimov@para.com");
        user.setId(userId);
        Response response = webTarget.path("/" + userId)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .put(Entity.entity(user, MediaType.APPLICATION_JSON));
        System.out.printf("Testing PUT /user/%d. Status=%s%n", userId, response.getStatus());
        System.out.println("\tEntity= " + response.readEntity(User.class));
    }

    private void testDelete(Long userId) {
        Response response = webTarget.path("/" + userId).request().accept(MediaType.MEDIA_TYPE_WILDCARD).delete();
        System.out.printf("Testing DELETE /user/%d. Status=%s%n", userId, response.getStatus());
    }
}
