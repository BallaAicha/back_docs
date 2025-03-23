package com.docmanager.dto;

import com.docmanager.domain.DocumentStatus;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

@Data
public class DocumentDTO {
    private Long id;
    private String name;
    private String description;
    private String version;
    private String fileType;
    private Long fileSize;
    private String filePath;
    private DocumentStatus status;
    private Long folderId;
    private Long parentDocumentId;
    private Set<Long> versionIds;
    private Set<String> tags;
    private Map<String, String> metadata;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}