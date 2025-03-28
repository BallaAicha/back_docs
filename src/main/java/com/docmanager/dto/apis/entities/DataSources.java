package com.docmanager.dto.apis.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "data_sources")
public class DataSources {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "rabbit_mq")
    private boolean rabbitMQ;

    @Column(name = "common_db")
    private boolean commonDB;

    @Column(name = "dedicated_db")
    private boolean dedicatedDB;

    @Column(name = "s3")
    private boolean s3;
}