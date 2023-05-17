/*
 * Copyright (c) 2023
 */
package com.mamay.resource;

import com.mamay.dao.UserDao;
import com.mamay.entity.User;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
public class UserResource {

  private final UserDao userDao = new UserDao();

  @GET
  @Produces(MediaType.WILDCARD)
  public Response retrieveAll() {
    GenericEntity<List<User>> entity = new GenericEntity<List<User>>(userDao.retrieveAll()) {};
    return Response.ok().entity(entity).build();
  }

  @GET
  @Path("/{userId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response retrieve(@PathParam("userId") Long userId) {
    return Response.ok().entity(userDao.retrieve(userId)).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  public Response create(User user) {
    Long userId = userDao.create(user);
    user.setId(userId);
    return Response.ok().entity(user).build();
  }

  @PUT
  @Path("/{userId}")
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response update(@PathParam("userId") Long userId, User user) {
    userDao.update(user);
    return Response.ok().entity(user).build();
  }

  @DELETE
  @Path("/{userId}")
  public Response delete(@PathParam("userId") Long userId) {
    userDao.delete(userId);
    return Response.noContent().build();
  }

  @Path("/{userId}/images")
  public ImageResource getImageResource() {
    return new ImageResource();
  }
}
