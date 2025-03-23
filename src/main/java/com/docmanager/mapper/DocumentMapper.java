package com.docmanager.mapper;

import com.docmanager.domain.Document;
import com.docmanager.dto.DocumentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DocumentMapper {
    @Mapping(target = "folderId", source = "folder.id")
    @Mapping(target = "parentDocumentId", source = "parentDocument.id")
    @Mapping(target = "versionIds", expression = "java(document.getVersions().stream().map(Document::getId).collect(java.util.stream.Collectors.toSet()))")
    DocumentDTO toDTO(Document document);

    @Mapping(target = "folder", ignore = true)
    @Mapping(target = "parentDocument", ignore = true)
    @Mapping(target = "versions", ignore = true)
    Document toEntity(DocumentDTO documentDTO);
}