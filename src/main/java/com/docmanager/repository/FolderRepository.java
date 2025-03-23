package com.docmanager.repository;

import com.docmanager.domain.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FolderRepository extends JpaRepository<Folder, Long> {
    @Query("SELECT f FROM Folder f WHERE f.parent IS NULL")
    List<Folder> findRootFolders();
    
    List<Folder> findByParentId(Long parentId);
}