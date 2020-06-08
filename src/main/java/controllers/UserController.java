package controllers;

import domain.DTOs.UserDTO;
import domain.models.User;
import filters.customAnnotations.JWTTokenNeeded;
import filters.customAnnotations.OnlyAdmin;
import jakarta.ws.rs.*;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import services.UserService;
import services.interfaces.IUserService;

import javax.ws.rs.*;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Response;

@Path("users")
public class UserController {

    private IUserService userService = new UserService();

    @GET
    public String hello() {
        return "Hello world!";
    }

    @JWTTokenNeeded
    @GET
    @Path("/{param}")
    public Response getUserByID(@PathParam("param") long id) {
        User user;
        try {
            user = userService.getUserByID(id);
        } catch (ServerErrorException ex) {
            return Response
                    .serverError().build();
        } catch (BadRequestException ex) {
            return Response
                    .status(Response.Status.ACCEPTED)
                    .entity("This user cannot be created").build();
        }


        if (user == null) {
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("User does not exist!")
                    .build();
        } else {
            return Response
                    .status(Response.Status.OK)
                    .build();
        }
    }

    @OnlyAdmin
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/add")
    public Response createNewUser(User user) {

        try {
            userService.addUser(user);
        } catch (ServerErrorException ex) {
            return Response
                    .serverError().entity(ex.getMessage()).build();
        } catch (BadRequestException ex) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage()).build();
        }

        return Response.
                status(Response.Status.CREATED)
                .entity("User created successfully!")
                .build();
    }


    @JWTTokenNeeded
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/update")
    public Response updateUser(User user, @Context ContainerRequestContext requestContext) {

        if (!requestContext.getSecurityContext().isUserInRole("admin") &&
                !requestContext.getSecurityContext().getUserPrincipal().getName().equals(user.getUsername())) {
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .build();
        }

        try {
            userService.updateUser(user);
        } catch (ServerErrorException ex) {
            return Response
                    .serverError().entity(ex.getMessage()).build();
        } catch (BadRequestException ex) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(ex.getMessage()).build();
        }

        return Response.
                status(Response.Status.OK)
                .entity("User updated successfully!")
                .build();
    }

}
