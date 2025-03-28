package com.docmanager.dto.apis.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "infrastructure")
public class Infrastructure {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "url_int", nullable = false)
    private String urlInt;

    @Column(name = "url_uat", nullable = false)
    private String urlUat;

    @Column(name = "url_oat", nullable = false)
    private String urlOat;

    @Column(name = "url_prod", nullable = false)
    private String urlProd;
}