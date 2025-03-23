package com.docmanager.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class FolderDTO {
    private Long id;
    private String name;
    private String description;
    private Long parentId;
    private Set<Long> subFolderIds;
    private Set<Long> documentIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}