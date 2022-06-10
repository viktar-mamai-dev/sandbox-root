package com.mamay.client;

import java.util.Arrays;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mamay.entity.Image;
import com.mamay.util.ImageUtil;

public class ImageClient extends BaseClient {

    private Long userId;

    public ImageClient(Long userId) {
        this.userId = userId;
        webTarget = webTarget.path("/users").path("/" + userId).path("/images");
    }

    private Long testUpload(byte[] data) {

        Response response = webTarget.request(MediaType.APPLICATION_XML).post(Entity.entity(data, MediaType.TEXT_PLAIN));
        System.out.println(String.format("Testing POST /users/%d/images. Status=%s", userId, response.getStatus()));
        Image uploadedImage = response.readEntity(Image.class);
        System.out.println("\tEntity id= " + uploadedImage.getId());
        return uploadedImage.getId();
    }

    private byte[] testDownload(Long imageId) {
        Response response = webTarget.path("/" + imageId).request().get();
        System.out.println(String.format("Testing GET /users/%d/images/%d. Status=%s", userId, imageId, response.getStatus()));
        byte data[] = response.readEntity(byte[].class);
        return data;
    }

    @Override
    public void run() {
        byte[] input = ImageUtil.retrieveImageFromFile();
        Long imageId = testUpload(input);
        byte[] output = testDownload(imageId);
        System.out.println(Arrays.equals(input, output));
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
