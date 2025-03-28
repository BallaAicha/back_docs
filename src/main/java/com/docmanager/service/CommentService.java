package com.docmanager.service;
import com.docmanager.domain.Comment;
import com.docmanager.domain.Document;
import com.docmanager.dto.CommentDTO;
import com.docmanager.exception.ResourceNotFoundException;
import com.docmanager.mapper.CommentMapper;
import com.docmanager.repository.CommentRepository;
import com.docmanager.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final DocumentRepository documentRepository;
    private final CommentMapper commentMapper;

    // Méthode pour récupérer les commentaires d'un document
    public List<CommentDTO> getCommentsByDocument(Long documentId) {
        return commentRepository.findByDocumentId(documentId).stream()
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CommentDTO addComment(Long documentId, CommentDTO dto, String username) {
        Document document = documentRepository.findById(documentId)
                .orElseThrow(() -> new ResourceNotFoundException("Document not found with ID: " + documentId));

        Comment comment = commentMapper.toEntity(dto);
        comment.setDocument(document);
        comment.setCreatedBy(username);

        return commentMapper.toDTO(commentRepository.save(comment));
    }

    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}