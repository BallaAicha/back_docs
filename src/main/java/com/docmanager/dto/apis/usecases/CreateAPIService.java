package com.docmanager.dto.apis.usecases;

import com.docmanager.dto.apis.APIServiceDTO;
import com.docmanager.dto.apis.Command;
import com.docmanager.dto.apis.CreateAPIServiceRequest;

public interface CreateAPIService extends Command {
    APIServiceDTO handle(CreateAPIServiceRequest input, RequestContext context);
}
