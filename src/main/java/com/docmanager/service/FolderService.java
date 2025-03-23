package com.docmanager.service;

import com.docmanager.domain.Folder;
import com.docmanager.dto.FolderDTO;
import com.docmanager.exception.ResourceNotFoundException;
import com.docmanager.mapper.FolderMapper;
import com.docmanager.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FolderService {
    private final FolderRepository folderRepository;
    private final FolderMapper folderMapper;

    public List<FolderDTO> getRootFolders() {
        return folderRepository.findRootFolders().stream()
                .map(folderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FolderDTO getFolder(Long id) {
        return folderRepository.findById(id)
                .map(folderMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Folder not found with id: " + id));
    }

    public FolderDTO createFolder(FolderDTO folderDTO) {
        Folder folder = folderMapper.toEntity(folderDTO);
        if (folderDTO.getParentId() != null) {
            Folder parent = folderRepository.findById(folderDTO.getParentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent folder not found with id: " + folderDTO.getParentId()));
            folder.setParent(parent);
        }
        return folderMapper.toDTO(folderRepository.save(folder));
    }

    public FolderDTO updateFolder(Long id, FolderDTO folderDTO) {
        return folderRepository.findById(id)
                .map(folder -> {
                    folder.setName(folderDTO.getName());
                    folder.setDescription(folderDTO.getDescription());
                    if (folderDTO.getParentId() != null && !folderDTO.getParentId().equals(folder.getParent().getId())) {
                        Folder newParent = folderRepository.findById(folderDTO.getParentId())
                                .orElseThrow(() -> new ResourceNotFoundException("Parent folder not found with id: " + folderDTO.getParentId()));
                        folder.setParent(newParent);
                    }
                    return folderMapper.toDTO(folderRepository.save(folder));
                })
                .orElseThrow(() -> new ResourceNotFoundException("Folder not found with id: " + id));
    }

    public void deleteFolder(Long id) {
        folderRepository.deleteById(id);
    }

    public List<FolderDTO> getSubFolders(Long parentId) {
        return folderRepository.findByParentId(parentId).stream()
                .map(folderMapper::toDTO)
                .collect(Collectors.toList());
    }
}