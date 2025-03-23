package com.docmanager.service;

import com.docmanager.domain.Document;
import com.docmanager.domain.Folder;
import com.docmanager.dto.DocumentDTO;
import com.docmanager.exception.ResourceNotFoundException;
import com.docmanager.mapper.DocumentMapper;
import com.docmanager.repository.DocumentRepository;
import com.docmanager.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final FolderRepository folderRepository;
    private final DocumentMapper documentMapper;

    public List<DocumentDTO> getDocumentsByFolder(Long folderId) {
        return documentRepository.findByFolderId(folderId).stream()
                .map(documentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DocumentDTO getDocument(Long id) {
        return documentRepository.findById(id)
                .map(documentMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + id));
    }

    public DocumentDTO createDocument(DocumentDTO documentDTO) {
        Document document = documentMapper.toEntity(documentDTO);
        
        // Set folder
        Folder folder = folderRepository.findById(documentDTO.getFolderId())
                .orElseThrow(() -> new ResourceNotFoundException("Folder not found with id: " + documentDTO.getFolderId()));
        document.setFolder(folder);
        
        // Set parent document if this is a new version
        if (documentDTO.getParentDocumentId() != null) {
            Document parentDocument = documentRepository.findById(documentDTO.getParentDocumentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent document not found with id: " + documentDTO.getParentDocumentId()));
            document.setParentDocument(parentDocument);
        }

        return documentMapper.toDTO(documentRepository.save(document));
    }

    public DocumentDTO updateDocument(Long id, DocumentDTO documentDTO) {
        return documentRepository.findById(id)
                .map(document -> {
                    document.setName(documentDTO.getName());
                    document.setDescription(documentDTO.getDescription());
                    document.setVersion(documentDTO.getVersion());
                    document.setStatus(documentDTO.getStatus());
                    document.setTags(documentDTO.getTags());
                    document.setMetadata(documentDTO.getMetadata());
                    
                    if (!document.getFolder().getId().equals(documentDTO.getFolderId())) {
                        Folder newFolder = folderRepository.findById(documentDTO.getFolderId())
                                .orElseThrow(() -> new ResourceNotFoundException("Folder not found with id: " + documentDTO.getFolderId()));
                        document.setFolder(newFolder);
                    }
                    
                    return documentMapper.toDTO(documentRepository.save(document));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + id));
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }

    public List<DocumentDTO> findDocumentsByTag(String tag) {
        return documentRepository.findByTagsContaining(tag).stream()
                .map(documentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<DocumentDTO> getDocumentVersions(Long documentId) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with id: " + documentId));
        
        return document.getVersions().stream()
                .map(documentMapper::toDTO)
                .collect(Collectors.toList());
    }
}