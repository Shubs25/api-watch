package com.apiwatch.contracts.events;

public class HealthCheckCompleted {
    private EventMetadata metadata;
    private HealthCheckCompletedPayload payload;

    public EventMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(EventMetadata metadata) {
        this.metadata = metadata;
    }

    public HealthCheckCompletedPayload getPayload() {
        return payload;
    }

    public void setPayload(HealthCheckCompletedPayload payload) {
        this.payload = payload;
    }
}
