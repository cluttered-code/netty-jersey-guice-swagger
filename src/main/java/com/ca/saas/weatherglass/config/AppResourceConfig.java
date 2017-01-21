package com.ca.saas.weatherglass.config;

import com.ca.saas.weatherglass.resource.PingResource;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * @author david.clutter@ca.com
 */
public class AppResourceConfig extends ResourceConfig {

    public AppResourceConfig() {
        // Resources
        packages(PingResource.class.getPackage().getName());

        // Swagger
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);

        register(Hk2GuiceBridgeFeature.class);
    }
}
