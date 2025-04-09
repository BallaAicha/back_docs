package com.docmanager.dto.apis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAPIServiceRequest {
    private String name;
    private String trigramme;
    private String description;





    private String databaseSchema;
    private InfrastructureDTO infrastructure;
    private DataSourcesDTO dataSources;
    private List<String> clientConsumers;
    private List<String> consumes;
    private List<String> consumedBy;
    private List<EndpointDTO> endpoints;
}