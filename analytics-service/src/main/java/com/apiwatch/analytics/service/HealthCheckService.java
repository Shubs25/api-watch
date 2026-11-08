package com.apiwatch.analytics.service;


import com.apiwatch.analytics.dao.HealthCheckDao;
import com.apiwatch.analytics.entity.HealthCheckEntity;
import com.apiwatch.contracts.events.EventMetadata;
import com.apiwatch.contracts.events.HealthCheckCompleted;
import com.apiwatch.contracts.events.HealthCheckCompletedPayload;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HealthCheckService {

    private final HealthCheckDao healthCheckDao;

    public HealthCheckService(HealthCheckDao healthCheckDao) {
        this.healthCheckDao = healthCheckDao;
    }

    @Transactional
    public void recordHealthCheck(HealthCheckCompleted event) {

        EventMetadata metadata = event.getMetadata();
        HealthCheckCompletedPayload payload = event.getPayload();

        HealthCheckEntity entity = new HealthCheckEntity();

        entity.setId(UUID.randomUUID());

        entity.setEventId(metadata.getEventId());
        entity.setCorrelationId(metadata.getCorrelationId());

        entity.setEndpointId(payload.getEndpointId());
        entity.setUrl(payload.getUrl());
        entity.setMethod(payload.getMethod());
        entity.setStatus(payload.getStatus());
        entity.setHttpStatus(payload.getHttpStatus());
        entity.setResponseTimeMs(payload.getResponseTimeMs());
        entity.setErrorMessage(payload.getErrorMessage());
        entity.setCheckedAt(payload.getCheckedAt());

        healthCheckDao.save(entity);
    }
}
