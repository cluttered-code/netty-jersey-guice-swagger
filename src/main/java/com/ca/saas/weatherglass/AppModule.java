package com.ca.saas.weatherglass;

import com.ca.saas.weatherglass.service.PongService;
import com.google.inject.AbstractModule;

/**
 * @author david.clutter@ca.com
 */
public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PongService.class);
    }
}
