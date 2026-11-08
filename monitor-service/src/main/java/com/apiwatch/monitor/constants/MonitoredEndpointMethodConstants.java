package com.apiwatch.monitor.constants;

import java.util.Arrays;

public enum MonitoredEndpointMethodConstants {
    GET("GET"),
    POST("POST"),
    PUT("PUT"),
    DELETE("DELETE"),
    PATCH("PATCH");

    private String methodString;

    private MonitoredEndpointMethodConstants(String string) {
        this.methodString = string;
    }

    public String getMethodString() {
        return methodString;
    }

    public static MonitoredEndpointMethodConstants fromString(String string) {
        if(string == null)
            return null;

        for(MonitoredEndpointMethodConstants constant : MonitoredEndpointMethodConstants.values())
            if(constant.getMethodString().equalsIgnoreCase(string))
                return constant;

        throw new IllegalArgumentException("No constant with value " + string + " found");
    }

}
