package com.docmanager.controller;

import com.docmanager.dto.DocumentDTO;
import com.docmanager.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
@Tag(name = "Document Management", description = "APIs for managing documents")
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping("/folder/{folderId}")
    @Operation(summary = "Get all documents in a folder")
    public ResponseEntity<List<DocumentDTO>> getDocumentsByFolder(@PathVariable Long folderId) {
        return ResponseEntity.ok(documentService.getDocumentsByFolder(folderId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a document by ID")
    public ResponseEntity<DocumentDTO> getDocument(@PathVariable Long id) {
        return ResponseEntity.ok(documentService.getDocument(id));
    }

    @GetMapping("/{id}/versions")
    @Operation(summary = "Get all versions of a document")
    public ResponseEntity<List<DocumentDTO>> getDocumentVersions(@PathVariable Long id) {
        return ResponseEntity.ok(documentService.getDocumentVersions(id));
    }

    @PostMapping
    @Operation(summary = "Create a new document")
    public ResponseEntity<DocumentDTO> createDocument(@RequestBody DocumentDTO documentDTO) {
        return ResponseEntity.ok(documentService.createDocument(documentDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a document")
    public ResponseEntity<DocumentDTO> updateDocument(@PathVariable Long id, @RequestBody DocumentDTO documentDTO) {
        return ResponseEntity.ok(documentService.updateDocument(id, documentDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a document")
    public ResponseEntity<Void> deleteDocument(@PathVariable Long id) {
        documentService.deleteDocument(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    @Operation(summary = "Search documents by tag")
    public ResponseEntity<List<DocumentDTO>> searchDocumentsByTag(@RequestParam String tag) {
        return ResponseEntity.ok(documentService.findDocumentsByTag(tag));
    }
}