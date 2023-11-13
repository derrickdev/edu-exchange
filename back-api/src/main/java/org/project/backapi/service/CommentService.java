package org.project.backapi.service;

import org.project.backapi.converter.CommentConverter;
import org.project.backapi.domain.Comment;
import org.project.backapi.domain.Post;
import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.CommentDto;
import org.project.backapi.dto.response.PagedResponse;
import org.project.backapi.enums.PostStatus;
import org.project.backapi.exception.ForbiddenAccessOperation;
import org.project.backapi.exception.RessourceNotFoundException;
import org.project.backapi.repository.CommentRepository;
import org.project.backapi.repository.PostRepository;
import org.project.backapi.repository.UserRepository;
import org.project.backapi.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentConverter commentConverter;

    public CommentDto createComment(CommentDto commentDto, User author) {
        Post post = postRepository.findById(commentDto.getPostId())
                .orElseThrow(() -> new RessourceNotFoundException(String.format("Post: %d does not exist", commentDto.getPostId())));

        if(post.getHidden() || post.getStatus().equals(PostStatus.CLOSED)) {
            throw new ForbiddenAccessOperation(String.format("You can no longer perform comment on this post"));
        }
        Comment parentComment = null;
        if (commentDto.getParentId() != null) {
            parentComment = commentRepository.findById(commentDto.getParentId())
                    .orElseThrow(() -> new RessourceNotFoundException(String.format("Provided comment parent: %d does not exist", commentDto.getParentId())));
        }

        Comment comment = commentConverter.convert(commentDto, author, parentComment, post);
        Comment savedComment = commentRepository.save(comment);

        return commentConverter.convert(comment);
    }

    public PagedResponse<CommentDto> getAllCommentsByPostId(Long postId, int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RessourceNotFoundException(
                        String.format("Post: %d does not exist", postId)));

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");

        Page<Comment> rootCommentsPage = commentRepository.findByParentIsNullAndPostId(postId, pageable);

        List<CommentDto> rootCommentDtos = rootCommentsPage.getContent().stream()
                .map(rootComment -> {
                    CommentDto dto = commentConverter.convert(rootComment);
                    dto.setReplies(getRepliesToComment(rootComment.getId()));
                    return dto;
                })
                .toList();

        return new PagedResponse<>(rootCommentDtos, rootCommentsPage.getNumber(), rootCommentsPage.getSize(),
                rootCommentsPage.getTotalElements(), rootCommentsPage.getTotalPages(), rootCommentsPage.isLast());

    }

    public List<CommentDto> getRepliesToComment(Long commentId) {
        List<Comment> replies = commentRepository.findByParentId(commentId);

        // return commentConverter.convert(commentRepository.findByParentId(commentId));
        return replies.stream()
                .map(reply -> {
                    CommentDto dto = commentConverter.convert(reply);
                    dto.setReplies(getRepliesToComment(reply.getId()));
                    return dto;
                })
                .toList();
    }

    public CommentDto updateComment(Long commentId, String newContent, User currentUser) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(
                        () -> new RessourceNotFoundException(String.format("Comment: %d does not exist ", commentId)));
        comment.setContent(newContent);
        Comment updatedComment = commentRepository.save(comment);

        return commentConverter.convert(updatedComment);
    }

    public String delete(Long id) {
        commentRepository.deleteById(id);
        return "Comment deleted successfully";
    }

    /*public Comment createComment(CommentDto commentDto, User currentUser) {
        Post post = postRepository.findById(commentDto.getPostId()).get();
        return commentRepository.save(commentConverter.convert(commentDto, userPrincipal, null, post));
    }*/
}
