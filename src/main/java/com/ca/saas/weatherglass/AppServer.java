package com.ca.saas.weatherglass;

import com.ca.saas.weatherglass.config.AppResourceConfig;
import io.netty.channel.Channel;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

/**
 * @author david.clutter@ca.com
 */
public class AppServer {

    private static final Logger LOG = LoggerFactory.getLogger(AppServer.class);
    private static final URI BASE_URI = URI.create("http://localhost:5050/");

    public static void main(final String... args) throws InterruptedException {
        final ResourceConfig resourceConfig = new AppResourceConfig();
        final Channel server = NettyHttpContainerProvider.createHttp2Server(BASE_URI, resourceConfig, null);

        createShutdownHook(server);
        LOG.info("HTTP/2 Server Started");
        LOG.info("Listening @ {}", BASE_URI);
        LOG.info("Ctrl+C to stop");
        Thread.currentThread().join();
    }

    private static void createShutdownHook(final Channel server) {
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> server.close())
        );
    }
}