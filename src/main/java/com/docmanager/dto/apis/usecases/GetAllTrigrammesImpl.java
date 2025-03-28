package com.docmanager.dto.apis.usecases;

import com.docmanager.dto.apis.GetAllTrigrammesRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllTrigrammesImpl implements GetAllTrigrammes {

    private final APIServiceRepository apiServiceRepository;

    @Override
    public List<String> handle(GetAllTrigrammesRequest input, RequestContext context) {
        return apiServiceRepository.findAllTrigrammes();
    }
}