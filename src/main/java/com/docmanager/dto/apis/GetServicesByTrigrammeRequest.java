package com.docmanager.dto.apis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetServicesByTrigrammeRequest {
    private String trigramme;
}