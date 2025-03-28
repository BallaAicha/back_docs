package com.docmanager.dto.apis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DataSourcesDTO {
    private boolean rabbitMQ;
    private boolean commonDB;
    private boolean dedicatedDB;
    private boolean s3;
}
