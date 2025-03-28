package com.docmanager.mapper;


import com.docmanager.domain.Comment;
import com.docmanager.dto.CommentDTO;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDTO toDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .documentId(comment.getDocument().getId())
                .createdBy(comment.getCreatedBy())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    public Comment toEntity(CommentDTO dto) {
        Comment comment = new Comment();
        comment.setContent(dto.getContent());
        return comment;
    }
}