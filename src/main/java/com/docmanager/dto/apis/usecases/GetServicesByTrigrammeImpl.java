package com.docmanager.dto.apis.usecases;

import com.docmanager.dto.apis.APIServiceDTO;
import com.docmanager.dto.apis.GetServicesByTrigrammeRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetServicesByTrigrammeImpl implements GetServicesByTrigramme {

    private final APIServiceRepository apiServiceRepository;
    private final APIServiceMapper apiServiceMapper;

    @Override
    public List<APIServiceDTO> handle(GetServicesByTrigrammeRequest input, RequestContext context) {
        return apiServiceRepository.findByTrigramme(input.getTrigramme()).stream()
                .map(apiServiceMapper::toDTO)
                .toList();
    }
}