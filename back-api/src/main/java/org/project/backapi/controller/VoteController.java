package org.project.backapi.controller;

import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;
import org.project.backapi.converter.VoteConverter;
import org.project.backapi.domain.Comment;
import org.project.backapi.domain.User;
import org.project.backapi.domain.Vote;
import org.project.backapi.dto.modelsDto.VoteDto;
import org.project.backapi.enums.VoteStatus;
import org.project.backapi.repository.CommentRepository;
import org.project.backapi.repository.UserRepository;
import org.project.backapi.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
public class VoteController {
    private final VoteService voteService;
    @Autowired
    private SessionFactory sessionFactory;

    private UserRepository userRepository;
    private CommentRepository commentRepository;

    @Autowired
    private EntityManager entityManager;


    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    /*@PostMapping("/{commentId}/{userId}/{voteStatus}")
    public ResponseEntity<Vote> createVote(@PathVariable Long commentId, @PathVariable Long userId, @PathVariable VoteStatus voteStatus) {
        Vote vote = voteService.createVote(commentId, userId, voteStatus);
        return new ResponseEntity<>(vote, HttpStatus.CREATED);
    }*/
    @PostMapping("/{commentId}/{voteStatus}")
    public ResponseEntity<VoteDto> createVote(@PathVariable Long commentId, @PathVariable VoteStatus voteStatus) {
        Vote vote = voteService.createVote(commentId, voteStatus);
        return new ResponseEntity<>(VoteConverter.convert(vote), HttpStatus.CREATED);
    }
    @PutMapping("/update/{voteId}/{newstatus}")
    public ResponseEntity<VoteDto> updateVote(@PathVariable Long voteId, @PathVariable VoteStatus newstatus) {
        Vote vote = voteService.updateVote(voteId, newstatus);
        return new ResponseEntity<>(VoteConverter.convert(vote), HttpStatus.OK);
    }


    @DeleteMapping("delete/{voteId}")
    public ResponseEntity<Object> deleteVote(@PathVariable Long voteId) {
        String response = voteService.delete(voteId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/countTrueVote/{commentId}")
    public ResponseEntity<Long> countTrueVotes(@PathVariable Long commentId) {
        long count = voteService.countTrueVotes(commentId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
    @GetMapping("/countFalseVote/{commentId}")
    public ResponseEntity<Long> countFalseVotes(@PathVariable Long commentId) {
        long count = voteService.countFalseVotes(commentId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }





}

