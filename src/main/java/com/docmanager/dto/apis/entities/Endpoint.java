package com.docmanager.dto.apis.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "endpoint")
public class Endpoint {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id")
    private APIService service;

    @Column(nullable = false, length = 10)
    private String method;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false)
    private String curl;

    @Column(name = "expected_response", nullable = false)
    private String expectedResponse;

    private String description;
}