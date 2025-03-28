package com.docmanager.dto.apis.usecases;

import com.docmanager.dto.apis.GetAllTrigrammesRequest;
import com.docmanager.dto.apis.Query;

import java.util.List;

public interface GetAllTrigrammes extends Query {
    List<String> handle(GetAllTrigrammesRequest input, RequestContext context);
}
