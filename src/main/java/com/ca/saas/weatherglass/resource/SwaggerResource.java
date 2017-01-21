package com.ca.saas.weatherglass.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author david.clutter@ca.com
 */
@Path("/")
public class SwaggerResource {

    @GET
    public Response redirect() throws URISyntaxException {
        SwaggerResource.class.getClassLoader().getResource("META-INF/resources/webjars/swagger-ui/2.2.8");
        return Response.temporaryRedirect(new URI("../docs?url=%2Fapi/swagger.json")).build();
    }
}
