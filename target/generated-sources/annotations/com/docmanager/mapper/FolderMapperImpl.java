package com.docmanager.mapper;

import com.docmanager.domain.Folder;
import com.docmanager.dto.FolderDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-22T03:21:32+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class FolderMapperImpl implements FolderMapper {

    @Override
    public FolderDTO toDTO(Folder folder) {
        if ( folder == null ) {
            return null;
        }

        FolderDTO folderDTO = new FolderDTO();

        folderDTO.setParentId( folderParentId( folder ) );
        folderDTO.setId( folder.getId() );
        folderDTO.setName( folder.getName() );
        folderDTO.setDescription( folder.getDescription() );
        folderDTO.setCreatedAt( folder.getCreatedAt() );
        folderDTO.setUpdatedAt( folder.getUpdatedAt() );

        folderDTO.setSubFolderIds( folder.getSubFolders().stream().map(Folder::getId).collect(java.util.stream.Collectors.toSet()) );
        folderDTO.setDocumentIds( folder.getDocuments().stream().map(doc -> doc.getId()).collect(java.util.stream.Collectors.toSet()) );

        return folderDTO;
    }

    @Override
    public Folder toEntity(FolderDTO folderDTO) {
        if ( folderDTO == null ) {
            return null;
        }

        Folder folder = new Folder();

        folder.setId( folderDTO.getId() );
        folder.setName( folderDTO.getName() );
        folder.setDescription( folderDTO.getDescription() );
        folder.setCreatedAt( folderDTO.getCreatedAt() );
        folder.setUpdatedAt( folderDTO.getUpdatedAt() );

        return folder;
    }

    private Long folderParentId(Folder folder) {
        if ( folder == null ) {
            return null;
        }
        Folder parent = folder.getParent();
        if ( parent == null ) {
            return null;
        }
        Long id = parent.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
