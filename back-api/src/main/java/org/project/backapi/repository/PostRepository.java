package org.project.backapi.repository;


import org.project.backapi.domain.Post;
import org.project.backapi.domain.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    //Page<Post> findAll(List<Post> setPosts, Pageable pageable);

    Page<Post> findAll(Pageable pageable);

    Page<Post> findByTopics(Topic topic, Pageable pageable);

    Page<Post> findByHiddenFalse(Pageable pageable);

    Page<Post> findByTopicsName(String topicName, Pageable pageable);

    Page<Post> findByTitleOrContent(String title, String content, Pageable pageable);

    @Query("SELECT p FROM Post p WHERE p.title ILIKE ?1 OR p.content ILIKE ?1")
    List<Post> searchPost(String query);

}
