package com.snowinpluto.demo.resource;

import com.google.inject.Inject;
import com.snowinpluto.demo.entity.User;
import com.snowinpluto.demo.service.UserService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    private UserService userService;

    @POST
    public User add(User user) {
        return userService.add(user);
    }

    @GET
    @Path("/{id}")
    public User findById(@PathParam("id") Long id) {
        return userService.findById(id);
    }
}
