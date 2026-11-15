package com.apiwatch.analytics.dto;

import com.apiwatch.contracts.events.CheckStatus;

import java.time.OffsetDateTime;
import java.util.UUID;

public class HealthCheckResponseDTO {
    private UUID endpointId;
    private CheckStatus status;
    private long responseTimeMs;
    private OffsetDateTime checkedAt;

    public UUID getEndpointId() {
        return endpointId;
    }

    public void setEndpointId(UUID endpointId) {
        this.endpointId = endpointId;
    }

    public CheckStatus getStatus() {
        return status;
    }

    public void setStatus(CheckStatus status) {
        this.status = status;
    }

    public long getResponseTimeMs() {
        return responseTimeMs;
    }

    public void setResponseTimeMs(long responseTimeMs) {
        this.responseTimeMs = responseTimeMs;
    }

    public OffsetDateTime getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(OffsetDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }
}
