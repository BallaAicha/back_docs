package com.docmanager.dto.apis.usecases;

import com.docmanager.dto.apis.APIServiceDTO;
import com.docmanager.dto.apis.GetServicesByTrigrammeRequest;
import com.docmanager.dto.apis.Query;

import java.util.List;

public interface GetServicesByTrigramme extends Query {
    List<APIServiceDTO> handle(GetServicesByTrigrammeRequest input, RequestContext context);
}
