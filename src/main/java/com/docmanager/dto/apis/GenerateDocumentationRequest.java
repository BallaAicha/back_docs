package com.docmanager.dto.apis;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenerateDocumentationRequest {
    private UUID serviceId;
}