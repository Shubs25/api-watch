package com.apiwatch.monitor.entity;

import com.apiwatch.monitor.constants.MonitoredEndpointMethodConstants;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity(name = "monitored_endpoint")
@Table(name = "monitored_endpoint")
public class MonitoredEndpointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(length = 100)
    private String name;

    @Column
    private String url;

    @Column
    @Enumerated(EnumType.STRING)
    private MonitoredEndpointMethodConstants method;

    @Column(name = "interval_seconds")
    private Integer intervalSeconds;

    @Column(name = "timeout_ms")
    private Integer timeoutMs;

    @Column
    private boolean active = true;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

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

    public Integer getIntervalSeconds() {
        return intervalSeconds;
    }

    public void setIntervalSeconds(Integer intervalSeconds) {
        this.intervalSeconds = intervalSeconds;
    }

    public Integer getTimeoutMs() {
        return timeoutMs;
    }

    public void setTimeoutMs(Integer timeoutMs) {
        this.timeoutMs = timeoutMs;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
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
