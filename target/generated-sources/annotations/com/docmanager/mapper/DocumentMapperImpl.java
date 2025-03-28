package com.docmanager.mapper;

import com.docmanager.domain.Document;
import com.docmanager.domain.Folder;
import com.docmanager.dto.DocumentDTO;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-28T14:10:47+0000",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.2 (Oracle Corporation)"
)
@Component
public class DocumentMapperImpl implements DocumentMapper {

    @Override
    public DocumentDTO toDTO(Document document) {
        if ( document == null ) {
            return null;
        }

        DocumentDTO documentDTO = new DocumentDTO();

        documentDTO.setFolderId( documentFolderId( document ) );
        documentDTO.setParentDocumentId( documentParentDocumentId( document ) );
        documentDTO.setId( document.getId() );
        documentDTO.setName( document.getName() );
        documentDTO.setDescription( document.getDescription() );
        documentDTO.setVersion( document.getVersion() );
        documentDTO.setFileType( document.getFileType() );
        documentDTO.setFileSize( document.getFileSize() );
        documentDTO.setFilePath( document.getFilePath() );
        documentDTO.setStatus( document.getStatus() );
        Set<String> set = document.getTags();
        if ( set != null ) {
            documentDTO.setTags( new LinkedHashSet<String>( set ) );
        }
        Map<String, String> map = document.getMetadata();
        if ( map != null ) {
            documentDTO.setMetadata( new LinkedHashMap<String, String>( map ) );
        }
        documentDTO.setCreatedAt( document.getCreatedAt() );
        documentDTO.setUpdatedAt( document.getUpdatedAt() );

        documentDTO.setVersionIds( document.getVersions().stream().map(Document::getId).collect(java.util.stream.Collectors.toSet()) );

        return documentDTO;
    }

    @Override
    public Document toEntity(DocumentDTO documentDTO) {
        if ( documentDTO == null ) {
            return null;
        }

        Document document = new Document();

        document.setId( documentDTO.getId() );
        document.setName( documentDTO.getName() );
        document.setDescription( documentDTO.getDescription() );
        document.setVersion( documentDTO.getVersion() );
        document.setFileType( documentDTO.getFileType() );
        document.setFileSize( documentDTO.getFileSize() );
        document.setFilePath( documentDTO.getFilePath() );
        document.setStatus( documentDTO.getStatus() );
        Set<String> set = documentDTO.getTags();
        if ( set != null ) {
            document.setTags( new LinkedHashSet<String>( set ) );
        }
        Map<String, String> map = documentDTO.getMetadata();
        if ( map != null ) {
            document.setMetadata( new LinkedHashMap<String, String>( map ) );
        }
        document.setCreatedAt( documentDTO.getCreatedAt() );
        document.setUpdatedAt( documentDTO.getUpdatedAt() );

        return document;
    }

    private Long documentFolderId(Document document) {
        if ( document == null ) {
            return null;
        }
        Folder folder = document.getFolder();
        if ( folder == null ) {
            return null;
        }
        Long id = folder.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long documentParentDocumentId(Document document) {
        if ( document == null ) {
            return null;
        }
        Document parentDocument = document.getParentDocument();
        if ( parentDocument == null ) {
            return null;
        }
        Long id = parentDocument.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
