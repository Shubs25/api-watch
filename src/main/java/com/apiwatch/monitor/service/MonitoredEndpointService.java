package com.apiwatch.monitor.service;

import com.apiwatch.monitor.dao.MonitoredEndpointDAO;
import com.apiwatch.monitor.dto.MonitoredEndpointRequestDTO;
import com.apiwatch.monitor.dto.MonitoredEndpointResponseDTO;
import com.apiwatch.monitor.entity.MonitoredEndpointEntity;
import com.apiwatch.monitor.exception.GenericServerSideException;
import com.apiwatch.monitor.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class MonitoredEndpointService {

    private final MonitoredEndpointDAO monitoredEndpointDAO;

    public MonitoredEndpointService(MonitoredEndpointDAO monitoredEndpointDAO) {
        this.monitoredEndpointDAO = monitoredEndpointDAO;
    }

    private MonitoredEndpointEntity convertToEntity(MonitoredEndpointRequestDTO dto) {
        MonitoredEndpointEntity entity = new MonitoredEndpointEntity();
        entity.setName(dto.getName());
        entity.setUrl(dto.getUrl());
        entity.setMethod(dto.getMethod());
        entity.setIntervalSeconds(dto.getInterval());
        entity.setTimeoutMs(dto.getTimeout());
        entity.setActive(dto.isActive());
        OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
        entity.setCreatedAt(now);
        entity.setUpdatedAt(now);
        return entity;
    }

    private MonitoredEndpointResponseDTO convertToResponseDTO(
            MonitoredEndpointEntity entity) {

        MonitoredEndpointResponseDTO dto =
                new MonitoredEndpointResponseDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setUrl(entity.getUrl());
        dto.setMethod(entity.getMethod());
        dto.setInterval(entity.getIntervalSeconds());
        dto.setTimeout(entity.getTimeoutMs());
        dto.setActive(entity.isActive());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());

        return dto;
    }

    public void validateAndInsert(MonitoredEndpointRequestDTO dto) {
        MonitoredEndpointEntity entity = convertToEntity(dto);
        if(monitoredEndpointDAO.findByUrl(dto.getUrl()).isPresent()) {
            System.out.println("An endpoint with the same url already exists");
            // handling duplicate entries will be implemented later
        }
        try {
           monitoredEndpointDAO.insertMonitoredEndpoint(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new GenericServerSideException("Something went wrong");
        }
    }

    @Transactional(readOnly = true)
    public List<MonitoredEndpointResponseDTO> getAllEndpoints() {

        return monitoredEndpointDAO.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public MonitoredEndpointResponseDTO getEndpointById(UUID id) {

        MonitoredEndpointEntity entity = monitoredEndpointDAO.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Endpoint with ID " + id + " not found"
                        )
                );

        return convertToResponseDTO(entity);
    }

    public MonitoredEndpointResponseDTO updateEndpoint(UUID id, MonitoredEndpointRequestDTO dto) {

        MonitoredEndpointEntity entity = monitoredEndpointDAO.findById(id)
                                                .orElseThrow(() -> new ResourceNotFoundException(
                                                             "Endpoint with ID " + id + " not found"
                                                    ));

        entity.setName(dto.getName());
        entity.setUrl(dto.getUrl());
        entity.setMethod(dto.getMethod());
        entity.setIntervalSeconds(dto.getInterval());
        entity.setTimeoutMs(dto.getTimeout());
        entity.setActive(dto.isActive());
        entity.setUpdatedAt(OffsetDateTime.now(ZoneOffset.UTC));

        monitoredEndpointDAO.update(entity);

        return convertToResponseDTO(entity);
    }

    @Transactional
    public void deleteEndpoint(UUID id) {

        MonitoredEndpointEntity entity =
                monitoredEndpointDAO.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException(
                                "Endpoint with ID " + id + " not found"
                        ));

        monitoredEndpointDAO.delete(entity);
    }

}
