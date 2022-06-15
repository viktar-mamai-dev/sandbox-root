package com.mamay.resource;

import com.mamay.dao.ImageDao;
import com.mamay.dao.UserDao;
import com.mamay.entity.Image;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public class ImageResource {

    private final ImageDao imageDao = new ImageDao();
    private final UserDao userDao = new UserDao();

    @POST
    public Response upload(@PathParam("userId") Long userId, byte[] data) {
        Image image = new Image(data);

        imageDao.save(image);
        userDao.upload(userId, image);

        return Response.status(200).entity(image).build();
    }

    @GET
    @Path("/{imageId}")
    public Response download(@PathParam("userId") Long userId, @PathParam("imageId") Long imageId) {
        Image image = imageDao.retrieve(imageId);

        return Response.ok().entity(image.getData()).build();
    }
}
