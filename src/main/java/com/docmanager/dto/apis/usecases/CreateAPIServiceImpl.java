package com.docmanager.dto.apis.usecases;

import com.docmanager.dto.apis.APIServiceDTO;
import com.docmanager.dto.apis.CreateAPIServiceRequest;
import com.docmanager.dto.apis.entities.APIService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreateAPIServiceImpl implements CreateAPIService {

    private final APIServiceRepository apiServiceRepository;
    private final APIServiceMapper apiServiceMapper;

    @Override
    @Transactional
    public APIServiceDTO handle(CreateAPIServiceRequest input, RequestContext context) {
        APIService entity = apiServiceMapper.fromRequest(input);
        entity.setId(UUID.randomUUID()); // Génération ID aléatoire explicite
        return apiServiceMapper.toDTO(apiServiceRepository.save(entity));
    }
}