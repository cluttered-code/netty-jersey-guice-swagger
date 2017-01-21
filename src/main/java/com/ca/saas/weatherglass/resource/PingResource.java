package com.ca.saas.weatherglass.resource;

import com.ca.saas.weatherglass.service.PongService;
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

    private final PongService pongService;

    @Inject
    private PingResource(final PongService pongService) {
        this.pongService = pongService;
    }

    @GET
    public String ping() {
        return pongService.getPong();
    }

    @GET
    @Path("/async")
    public void asyncPing(@Suspended final AsyncResponse response) {
        new Thread(() ->
            response.resume(Response.ok(pongService.getPong()).build())
        ).start();
    }
}