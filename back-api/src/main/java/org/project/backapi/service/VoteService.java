package org.project.backapi.service;

import org.project.backapi.domain.Comment;
import org.project.backapi.domain.User;
import org.project.backapi.domain.Vote;
import org.project.backapi.enums.VoteStatus;
import org.project.backapi.exception.RessourceNotFoundException;
import org.project.backapi.repository.CommentRepository;
import org.project.backapi.repository.UserRepository;
import org.project.backapi.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    @Autowired
    public VoteService(
            VoteRepository voteRepository,
            CommentRepository commentRepository,
            UserRepository userRepository) {

        this.voteRepository = voteRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public Vote createVote(Long commentId, VoteStatus voteStatus) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RessourceNotFoundException("Comment", "id", commentId));

        Vote vote= new Vote();
        vote.setComment(comment);
        vote.setUser(currentUser);
        vote.setVoteStatus(voteStatus);
        return voteRepository.save(vote);
    }
    public Vote updateVote(Long voteId, VoteStatus newStatus) {
        Vote vote = voteRepository.findById(voteId)
                .orElseThrow(() -> new RessourceNotFoundException("Vote", "id", voteId));

        vote.setVoteStatus(newStatus);
        return voteRepository.save(vote);
    }


    public String delete(Long voteId) {
        if (!voteRepository.existsById(voteId)) {
            throw new RessourceNotFoundException("Vote", "id", voteId);
        }
        voteRepository.deleteById(voteId);
        return "Vote with id " + voteId + " has been deleted successfully.";
    }
    public long countVotes(Long commentId, VoteStatus voteStatus) {
        return voteRepository.countByCommentIdAndStatus(commentId, voteStatus);
    }

    public long countTrueVotes(Long commentId) {
        return countVotes(commentId, VoteStatus.TRUE);
    }
    public long countFalseVotes(Long commentId) {
        return countVotes(commentId, VoteStatus.FALSE);
    }



}
