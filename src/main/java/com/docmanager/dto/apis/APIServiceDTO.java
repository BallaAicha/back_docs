package com.docmanager.dto.apis;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class APIServiceDTO {
    private UUID id;
    private String name;
    private String trigramme;
    private String description;
    private InfrastructureDTO infrastructure;
    private DataSourcesDTO dataSources;
    private List<String> clientConsumers;
    private List<String> consumes;
    private List<String> consumedBy;
    private List<EndpointDTO> endpoints;
    private String databaseSchema;
}

