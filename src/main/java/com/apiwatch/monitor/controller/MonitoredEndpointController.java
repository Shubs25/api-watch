package com.apiwatch.monitor.controller;
import com.apiwatch.monitor.dto.MonitoredEndpointRequestDTO;
import com.apiwatch.monitor.dto.MonitoredEndpointResponseDTO;
import com.apiwatch.monitor.service.MonitoredEndpointService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/endpoints")
public class MonitoredEndpointController {

    final MonitoredEndpointService monitoredEndpointService;

    public MonitoredEndpointController(MonitoredEndpointService monitoredEndpointService) {
        this.monitoredEndpointService = monitoredEndpointService;
    }

    @PostMapping
    public ResponseEntity<Object> postMonitoredEndpoint(@Valid @RequestBody MonitoredEndpointRequestDTO body) {
        monitoredEndpointService.validateAndInsert(body);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<MonitoredEndpointResponseDTO>> getAllEndpoints() {

        return ResponseEntity.ok(
                monitoredEndpointService.getAllEndpoints()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MonitoredEndpointResponseDTO> getEndpointById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                monitoredEndpointService.getEndpointById(id)
        );
    }
}
