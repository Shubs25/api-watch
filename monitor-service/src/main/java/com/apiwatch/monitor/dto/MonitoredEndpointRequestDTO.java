package com.apiwatch.monitor.dto;

import com.apiwatch.monitor.constants.MonitoredEndpointMethodConstants;
import jakarta.validation.constraints.*;


public class MonitoredEndpointRequestDTO {

    @NotBlank(message = "Please Enter Name")
    @Size(max = 128, message = "Name cannot exceed 128 characters")
    private String name;
    @NotBlank(message = "Please Enter URL")
    @Size(max = 2048, message = "URL cannot exceed 2048 characters")
    private String url;
    @NotNull(message = "Please Enter Method")
    private MonitoredEndpointMethodConstants method;
    @NotNull
    @Min(value = 32, message = "Interval must be greater than or equal to 32")
    @Max(value = 300, message = "Interval must be lesser than or equal to 300")
    private Integer interval;
    @NotNull
    @Positive(message = "Timeout must be positive")
    @Max(value = 12000, message = "Timeout must be lesser than or equal to 12,000")
    private Integer timeout;
    @NotNull(message = "Please Enter Active")
    private Boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MonitoredEndpointMethodConstants getMethod() {
        return method;
    }

    public void setMethod(MonitoredEndpointMethodConstants method) {
        this.method = method;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
