package com.ca.saas.weatherglass.resource;

import io.swagger.annotations.Api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

/**
 * @author david.clutter@ca.com
 */
@Path("ping")
@Api("Ping")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class PingResource {

    public static final String PONG = "\"pong\"";

    @Inject
    private PingResource() {}

    @GET
    public String ping() {
        return PONG;
    }

    @GET
    @Path("/async")
    public void asyncPing(@Suspended final AsyncResponse response) {
        new Thread(() ->
            response.resume(Response.ok(PONG).build())
        ).start();
    }
}