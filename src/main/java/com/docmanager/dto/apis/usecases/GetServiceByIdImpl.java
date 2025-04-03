package com.docmanager.dto.apis.usecases;
import com.docmanager.dto.apis.APIServiceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetServiceByIdImpl implements GetServiceById {
    private final APIServiceRepository apiServiceRepository;
    private final APIServiceMapper apiServiceMapper;
    @Override
    public APIServiceDTO handle(UUID id, RequestContext context) {
        return apiServiceRepository.findById(id)
                .map(apiServiceMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("APIService not found with id: " + id));
    }
}