package com.docmanager.dto.apis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EndpointDTO {
    private String method;
    private String path;
    private String curl;
    private String expectedResponse;
    private String description;
}
