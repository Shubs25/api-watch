package com.apiwatch.analytics.entity;


import com.apiwatch.contracts.events.CheckStatus;
import jakarta.persistence.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "health_checks")
public class HealthCheckEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID eventId;

    @Column(nullable = false)
    private UUID correlationId;

    @Column(nullable = false)
    private UUID endpointId;

    @Column(nullable = false, length = 200)
    private String url;

    @Column(nullable = false, length = 10)
    private String method;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 11)
    private CheckStatus status;

    private Integer httpStatus;

    @Column(nullable = false)
    private long responseTimeMs;

    @Column(length = 1000)
    private String errorMessage;

    @Column(nullable = false, columnDefinition = "datetimeoffset")
    private OffsetDateTime checkedAt;

    public HealthCheckEntity() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getEventId() {
        return eventId;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(UUID correlationId) {
        this.correlationId = correlationId;
    }

    public UUID getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(UUID endpointId) {
        this.endpointId = endpointId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public CheckStatus getStatus() {
        return status;
    }

    public void setStatus(CheckStatus status) {
        this.status = status;
    }

    public Integer getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(Integer httpStatus) {
        this.httpStatus = httpStatus;
    }

    public long getResponseTimeMs() {
        return responseTimeMs;
    }

    public void setResponseTimeMs(long responseTimeMs) {
        this.responseTimeMs = responseTimeMs;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public OffsetDateTime getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(OffsetDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }

    // Generate getters and setters
}
