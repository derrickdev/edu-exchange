package org.project.backapi.converter;

import org.project.backapi.domain.Comment;
import org.project.backapi.domain.Post;
import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.CommentDto;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentConverter {
    public CommentDto convert(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());
        dto.setImagePaths(comment.getImagePaths());
        dto.setAuthorId(comment.getUser().getId());
        dto.setPostId(comment.getPost().getId());
        if (comment.getParent() != null) {
            dto.setParentId(comment.getParent().getId());
        }
        // dto.setReplies(getReplies(comment));
        dto.setVotesCount(CollectionUtils.isEmpty(comment.getVotes()) ? 0 : comment.getVotes().size());


        return dto;
    }

    public List<CommentDto> convert(List<Comment> comments) {

        return comments.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    public Comment convert(CommentDto dto, User author, Comment parent, Post post) {
        Comment comment = Comment.builder()
                .content(dto.getContent())
                .parent(parent)
                .post(post)
                .user(author)
                .build();

        return comment;
    }
}
