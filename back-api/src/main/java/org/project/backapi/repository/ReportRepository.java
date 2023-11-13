package org.project.backapi.repository;

import org.project.backapi.domain.Post;
import org.project.backapi.domain.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    Page<Report> findAll(Pageable pageable);

    Page<Report> findByPost_HiddenFalse(Pageable pageable);

    Page<Report> findAllByPost(Post post, Pageable pageable);
}
