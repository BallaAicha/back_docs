package com.docmanager.dto.apis.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Table(name = "api_service")
public class APIService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 3)
    private String trigramme;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "infrastructure_id")
    private Infrastructure infrastructure;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "data_sources_id")
    private DataSources dataSources;

    @Column(name = "database_schema")
    private String databaseSchema;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClientConsumer> clientConsumers;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Consumes> consumes;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ConsumedBy> consumedBy;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endpoint> endpoints;

    @CreationTimestamp
    @Column(name = "created_at")
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private ZonedDateTime updatedAt;

    // Nouveaux attributs
    @Column(name = "bridge_communication")
    private boolean bridgeCommunication;

    @Column(name = "criticality")
    private String criticality;

    @Column(name = "po_coedev")
    private String poCoedev;

    @Column(name = "techlead")
    private String techlead;

    @Column(name = "java17_migrated")
    private boolean java17Migrated;

    @Column(name = "sonarized")
    private boolean sonarized;

    @Column(name = "sonar_report_url")
    private String sonarReportUrl;

    @Column(name = "version")
    private String version;



}