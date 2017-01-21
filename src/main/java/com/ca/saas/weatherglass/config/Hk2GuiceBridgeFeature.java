package com.ca.saas.weatherglass.config;

import com.ca.saas.weatherglass.AppModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.glassfish.hk2.api.ServiceLocator;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.inject.Inject;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

/**
 * @author david.clutter@ca.com
 */
public class Hk2GuiceBridgeFeature implements Feature {

    @Inject
    public Hk2GuiceBridgeFeature(final ServiceLocator serviceLocator) {
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        final GuiceIntoHK2Bridge g2h = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        final Injector injector = Guice.createInjector(new AppModule());
        g2h.bridgeGuiceInjector(injector);
    }

    @Override
    public boolean configure(final FeatureContext featureContext) {
        return true;
    }
}