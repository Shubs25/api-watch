package com.apiwatch.contracts.events;

public class HealthCheckRequested {

    private EventMetadata metadata;
    private HealthCheckRequestedPayload payload;

    public HealthCheckRequestedPayload getPayload() {
        return payload;
    }

    public void setPayload(HealthCheckRequestedPayload payload) {
        this.payload = payload;
    }

    public EventMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(EventMetadata metadata) {
        this.metadata = metadata;
    }

}
