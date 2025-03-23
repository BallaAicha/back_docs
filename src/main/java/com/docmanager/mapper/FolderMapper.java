package com.docmanager.mapper;

import com.docmanager.domain.Folder;
import com.docmanager.dto.FolderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FolderMapper {
    @Mapping(target = "parentId", source = "parent.id")
    @Mapping(target = "subFolderIds", expression = "java(folder.getSubFolders().stream().map(Folder::getId).collect(java.util.stream.Collectors.toSet()))")
    @Mapping(target = "documentIds", expression = "java(folder.getDocuments().stream().map(doc -> doc.getId()).collect(java.util.stream.Collectors.toSet()))")
    FolderDTO toDTO(Folder folder);

    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "subFolders", ignore = true)
    @Mapping(target = "documents", ignore = true)
    Folder toEntity(FolderDTO folderDTO);
}