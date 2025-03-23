package com.docmanager.repository;

import com.docmanager.domain.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByFolderId(Long folderId);
    List<Document> findByTagsContaining(String tag);
}