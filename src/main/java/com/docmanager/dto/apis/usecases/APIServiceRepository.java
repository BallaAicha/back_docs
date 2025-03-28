package com.docmanager.dto.apis.usecases;

import com.docmanager.dto.apis.entities.APIService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface APIServiceRepository extends JpaRepository<APIService, UUID> {
    List<APIService> findByTrigramme(String trigramme);

    @Query("SELECT DISTINCT a.trigramme FROM APIService a ORDER BY a.trigramme")
    List<String> findAllTrigrammes();
}