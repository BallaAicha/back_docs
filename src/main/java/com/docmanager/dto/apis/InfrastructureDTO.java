package com.docmanager.dto.apis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InfrastructureDTO {
    private String urlInt;
    private String urlUat;
    private String urlOat;
    private String urlProd;
}
