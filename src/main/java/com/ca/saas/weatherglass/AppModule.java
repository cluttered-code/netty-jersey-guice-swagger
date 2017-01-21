package com.ca.saas.weatherglass;

import com.ca.saas.weatherglass.resource.PingResource;
import com.google.common.reflect.ClassPath;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;

/**
 * @author david.clutter@ca.com
 */
public class AppModule extends AbstractModule {

    private static final String RESOURCE_PACKAGE = PingResource.class.getPackage().getName();

    @Override
    protected void configure() {
        // Swagger Resources
        bind(ApiListingResource.class);
        bind(SwaggerSerializers.class);

    }

    @Provides
    @Singleton
    public ResourceConfig provideResourceConfig(final ApiListingResource apiListingResource,
                                                final SwaggerSerializers swaggerSerializers) throws IOException {

        final ResourceConfig resourceConfig = new ResourceConfig();
        registerPackageResources(resourceConfig);
        resourceConfig.registerInstances(apiListingResource, swaggerSerializers);
        return resourceConfig;
    }

    private void registerPackageResources(final ResourceConfig resourceConfig) throws IOException {
        ClassPath.from(getClass().getClassLoader())
                .getTopLevelClasses(RESOURCE_PACKAGE)
                .forEach(classInfo ->
                        resourceConfig.register(classInfo.load()));
    }
}
