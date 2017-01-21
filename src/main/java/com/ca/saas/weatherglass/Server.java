package com.ca.saas.weatherglass;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.netty.channel.Channel;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

/**
 * @author david.clutter@ca.com
 */
public class Server {

    private static final URI BASE_URI = URI.create("http://localhost:5050/");

    public static void main(String[] args) throws InterruptedException {
        final Injector injector = Guice.createInjector(new AppModule());

        final Channel server = NettyHttpContainerProvider.createHttp2Server(BASE_URI, injector.getInstance(ResourceConfig.class), null);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> server.close())
        );

        System.out.println(String.format("Application started. (HTTP/2 enabled!)\nlistening @ %s\nStop the application using CTRL+C.", BASE_URI));

        Thread.currentThread().join();
    }
}