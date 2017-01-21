package com.ca.saas.weatherglass.service;

import com.google.inject.Inject;

/**
 * @author david.clutter@ca.com
 */
public class PongService {

    public static final String PONG = "\"pong\"";

    @Inject
    private PongService() {}

    public String getPong() {
        return PONG;
    }
}
