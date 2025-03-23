package com.docmanager.controller;

import com.docmanager.dto.FolderDTO;
import com.docmanager.service.FolderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/folders")
@RequiredArgsConstructor
@Tag(name = "Folder Management", description = "APIs for managing folders")
public class FolderController {
    private final FolderService folderService;

    @GetMapping("/root")
    @Operation(summary = "Get all root folders")
    public ResponseEntity<List<FolderDTO>> getRootFolders() {
        return ResponseEntity.ok(folderService.getRootFolders());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a folder by ID")
    public ResponseEntity<FolderDTO> getFolder(@PathVariable Long id) {
        return ResponseEntity.ok(folderService.getFolder(id));
    }

    @PostMapping
    @Operation(summary = "Create a new folder")
    public ResponseEntity<FolderDTO> createFolder(@RequestBody FolderDTO folderDTO) {
        return ResponseEntity.ok(folderService.createFolder(folderDTO));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a folder")
    public ResponseEntity<FolderDTO> updateFolder(@PathVariable Long id, @RequestBody FolderDTO folderDTO) {
        return ResponseEntity.ok(folderService.updateFolder(id, folderDTO));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a folder")
    public ResponseEntity<Void> deleteFolder(@PathVariable Long id) {
        folderService.deleteFolder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{parentId}/subfolders")
    @Operation(summary = "Get all subfolders of a folder")
    public ResponseEntity<List<FolderDTO>> getSubFolders(@PathVariable Long parentId) {
        return ResponseEntity.ok(folderService.getSubFolders(parentId));
    }
}