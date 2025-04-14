package com.docmanager.dto.apis.usecases;

import com.docmanager.dto.apis.*;
import com.docmanager.dto.apis.entities.*;
import org.springframework.stereotype.Component;

@Component
public class APIServiceMapper {

//    public APIServiceDTO toDTO(APIService entity) {
//        APIServiceDTO dto = new APIServiceDTO();
//        dto.setId(entity.getId());
//        dto.setName(entity.getName());
//        dto.setTrigramme(entity.getTrigramme());
//        dto.setDescription(entity.getDescription());
//        dto.setDatabaseSchema(entity.getDatabaseSchema());
//
//        // Infrastructure
//        dto.setInfrastructure(toInfrastructureDTO(entity.getInfrastructure()));
//
//        // DataSources
//        dto.setDataSources(toDataSourceDTO(entity.getDataSources()));
//
//        dto.setClientConsumers(entity.getClientConsumers()
//                .stream().map(ClientConsumer::getName).toList());
//
//        dto.setConsumes(entity.getConsumes()
//                .stream().map(Consumes::getName).toList());
//
//        dto.setConsumedBy(entity.getConsumedBy()
//                .stream().map(ConsumedBy::getName).toList());
//
//        dto.setEndpoints(entity.getEndpoints()
//                .stream().map(this::toEndpointDTO).toList());
//
//        return dto;
//    }
//
//    public APIService fromRequest(CreateAPIServiceRequest request) {
//        APIService service = new APIService();
//        updateEntityFromRequest(service, request);
//        return service;
//    }
//
//    public void updateEntityFromRequest(APIService entity, CreateAPIServiceRequest request) {
//        entity.setName(request.getName());
//        entity.setTrigramme(request.getTrigramme());
//        entity.setDescription(request.getDescription());
//        entity.setDatabaseSchema(request.getDatabaseSchema());
//        entity.setInfrastructure(toInfrastructure(request.getInfrastructure()));
//        entity.setDataSources(toDataSources(request.getDataSources()));
//
//        // set consumers, consumedBy, consumes, endpoints
//        entity.setClientConsumers(request.getClientConsumers().stream()
//                .map(name -> {
//                    ClientConsumer cc = new ClientConsumer();
//                    cc.setName(name);
//                    cc.setService(entity);
//                    return cc;
//                }).toList());
//
//        entity.setConsumes(request.getConsumes().stream()
//                .map(name -> {
//                    Consumes c = new Consumes();
//                    c.setName(name);
//                    c.setService(entity);
//                    return c;
//                }).toList());
//
//        entity.setConsumedBy(request.getConsumedBy().stream()
//                .map(name -> {
//                    ConsumedBy cb = new ConsumedBy();
//                    cb.setName(name);
//                    cb.setService(entity);
//                    return cb;
//                }).toList());
//
//        entity.setEndpoints(request.getEndpoints().stream()
//                .map(dto -> {
//                    Endpoint endpoint = new Endpoint();
//                    endpoint.setMethod(dto.getMethod());
//                    endpoint.setPath(dto.getPath());
//                    endpoint.setCurl(dto.getCurl());
//                    endpoint.setExpectedResponse(dto.getExpectedResponse());
//                    endpoint.setDescription(dto.getDescription());
//                    endpoint.setService(entity);
//                    return endpoint;
//                }).toList());
//    }


//    public APIServiceDTO toDTO(APIService entity) {
//        APIServiceDTO dto = new APIServiceDTO();
//        dto.setId(entity.getId());
//        dto.setName(entity.getName());
//        dto.setTrigramme(entity.getTrigramme());
//        dto.setDescription(entity.getDescription());
//        dto.setDatabaseSchema(entity.getDatabaseSchema());
//
//
//        dto.setBridgeCommunication(entity.isBridgeCommunication());
//        dto.setCriticality(entity.getCriticality());
//        dto.setPoCoedev(entity.getPoCoedev());
//        dto.setTechlead(entity.getTechlead());
//        dto.setJava17Migrated(entity.isJava17Migrated());
//        dto.setSonarized(entity.isSonarized());
//        dto.setSonarReportUrl(entity.getSonarReportUrl());
//        dto.setVersion(entity.getVersion());
//
//
//        // Infrastructure
//        dto.setInfrastructure(toInfrastructureDTO(entity.getInfrastructure()));
//
//        // DataSources
//        dto.setDataSources(toDataSourceDTO(entity.getDataSources()));
//
//        dto.setClientConsumers(entity.getClientConsumers()
//                .stream().map(ClientConsumer::getName).toList());
//
//        dto.setConsumes(entity.getConsumes()
//                .stream().map(Consumes::getName).toList());
//
//        dto.setConsumedBy(entity.getConsumedBy()
//                .stream().map(ConsumedBy::getName).toList());
//
//        dto.setEndpoints(entity.getEndpoints()
//                .stream().map(this::toEndpointDTO).toList());
//
//        return dto;
//    }

    public APIService fromRequest(CreateAPIServiceRequest request) {
        APIService service = new APIService();
        updateEntityFromRequest(service, request);
        return service;
    }

    public void updateEntityFromRequest(APIService entity, CreateAPIServiceRequest request) {
        entity.setName(request.getName());
        entity.setTrigramme(request.getTrigramme());
        entity.setDescription(request.getDescription());
        entity.setDatabaseSchema(request.getDatabaseSchema());



        entity.setInfrastructure(toInfrastructure(request.getInfrastructure()));
        entity.setDataSources(toDataSources(request.getDataSources()));

        // set consumers, consumedBy, consumes, endpoints
        entity.setClientConsumers(request.getClientConsumers().stream()
                .map(name -> {
                    ClientConsumer cc = new ClientConsumer();
                    cc.setName(name);
                    cc.setService(entity);
                    return cc;
                }).toList());

        entity.setConsumes(request.getConsumes().stream()
                .map(name -> {
                    Consumes c = new Consumes();
                    c.setName(name);
                    c.setService(entity);
                    return c;
                }).toList());

        entity.setConsumedBy(request.getConsumedBy().stream()
                .map(name -> {
                    ConsumedBy cb = new ConsumedBy();
                    cb.setName(name);
                    cb.setService(entity);
                    return cb;
                }).toList());

        entity.setEndpoints(request.getEndpoints().stream()
                .map(dto -> {
                    Endpoint endpoint = new Endpoint();
                    endpoint.setMethod(dto.getMethod());
                    endpoint.setPath(dto.getPath());
                    endpoint.setCurl(dto.getCurl());
                    endpoint.setExpectedResponse(dto.getExpectedResponse());
                    endpoint.setDescription(dto.getDescription());
                    endpoint.setService(entity);
                    return endpoint;
                }).toList());
    }


    private InfrastructureDTO toInfrastructureDTO(Infrastructure infra){
        return infra == null ? null : new InfrastructureDTO(
                infra.getUrlInt(),
                infra.getUrlUat(),
                infra.getUrlOat(),
                infra.getUrlProd());
    }

    private Infrastructure toInfrastructure(InfrastructureDTO dto){
        Infrastructure infra = new Infrastructure();
        infra.setUrlInt(dto.getUrlInt());
        infra.setUrlUat(dto.getUrlUat());
        infra.setUrlOat(dto.getUrlOat());
        infra.setUrlProd(dto.getUrlProd());
        return infra;
    }

    private DataSourcesDTO toDataSourceDTO(DataSources ds){
        return ds == null ? null : new DataSourcesDTO(
                ds.isRabbitMQ(),
                ds.isCommonDB(),
                ds.isDedicatedDB(),
                ds.isS3());
    }

    private DataSources toDataSources(DataSourcesDTO dto){
        DataSources ds = new DataSources();
        ds.setRabbitMQ(dto.isRabbitMQ());
        ds.setCommonDB(dto.isCommonDB());
        ds.setDedicatedDB(dto.isDedicatedDB());
        ds.setS3(dto.isS3());
        return ds;
    }

    private EndpointDTO toEndpointDTO(Endpoint e){
        return new EndpointDTO(
                e.getMethod(),
                e.getPath(),
                e.getCurl(),
                e.getExpectedResponse(),
                e.getDescription());
    }
}