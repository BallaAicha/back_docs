package com.docmanager.controller;

import com.docmanager.dto.apis.APIServiceDTO;
import com.docmanager.dto.apis.CreateAPIServiceRequest;
import com.docmanager.dto.apis.GetAllTrigrammesRequest;
import com.docmanager.dto.apis.GetServicesByTrigrammeRequest;
import com.docmanager.dto.apis.usecases.CreateAPIService;
import com.docmanager.dto.apis.usecases.GetAllTrigrammes;
import com.docmanager.dto.apis.usecases.GetServicesByTrigramme;
import com.docmanager.dto.apis.usecases.RequestContext;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class APIServiceAPI implements CreateAPIService {

    @Qualifier("createAPIServiceImpl")
    private final CreateAPIService createAPIService;

    private final GetServicesByTrigramme getServicesByTrigramme;
    @GetMapping("/trigramme/{trigramme}")
    public List<APIServiceDTO> handle(GetServicesByTrigrammeRequest input, @ModelAttribute RequestContext context) {
        return getServicesByTrigramme.handle(input, context);
    }

    @PostMapping
    public APIServiceDTO handle(@RequestBody CreateAPIServiceRequest input, @ModelAttribute RequestContext context) {
        return createAPIService.handle(input, context);
    }

    private final GetAllTrigrammes getAllTrigrammes;
    @GetMapping("/trigrammes")
    public List<String> handle(GetAllTrigrammesRequest input, @ModelAttribute RequestContext context) {
        return getAllTrigrammes.handle(input, context);
    }



}