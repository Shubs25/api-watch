package com.apiwatch.monitor.dto;

import com.apiwatch.monitor.constants.MonitoredEndpointMethodConstants;

import java.time.OffsetDateTime;
import java.util.UUID;

public class MonitoredEndpointResponseDTO {

    private UUID id;
    private String name;
    private String url;
    private MonitoredEndpointMethodConstants method;
    private Integer interval;
    private Integer timeout;
    private Boolean active;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}

