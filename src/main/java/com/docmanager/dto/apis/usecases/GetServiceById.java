package com.docmanager.dto.apis.usecases;

import com.docmanager.dto.apis.APIServiceDTO;
import com.docmanager.dto.apis.Query;

import java.util.UUID;

public interface GetServiceById extends Query {
    APIServiceDTO handle(UUID id, RequestContext context);
}
