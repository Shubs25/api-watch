package com.apiwatch.analytics.controller;


import com.apiwatch.analytics.dto.HealthCheckResponseDTO;
import com.apiwatch.analytics.service.HealthCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/endpoints")
public class HealthCheckController {

    private HealthCheckService healthCheckService;

    public HealthCheckController(HealthCheckService healthCheckService) {
        this.healthCheckService = healthCheckService;
    }

    @GetMapping("/{id}/health")
    public ResponseEntity<HealthCheckResponseDTO> health(@PathVariable UUID id) {
        return ResponseEntity
                .ok(healthCheckService.getLatestHealthCheckRecordByEndpointId(id));
    }

}