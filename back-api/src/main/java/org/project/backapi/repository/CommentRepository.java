package org.project.backapi.repository;

import org.project.backapi.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    Page<Comment> findByParentIsNullAndPostId(Long postId, Pageable pageable);

    List<Comment> findByPostId(Long postId);

    List<Comment> findByParentId(Long commentId);
}
