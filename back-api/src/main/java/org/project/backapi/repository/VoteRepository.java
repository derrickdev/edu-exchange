package org.project.backapi.repository;

import org.project.backapi.domain.Vote;
import org.project.backapi.enums.VoteStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepository extends JpaRepository<Vote, Long>  {

    long countByCommentIdAndStatus(Long commentId, VoteStatus voteStatus);
}
