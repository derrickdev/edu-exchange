package org.project.backapi.controller;

import jakarta.validation.Valid;
import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.CommentDto;
import org.project.backapi.repository.UserRepository;
import org.project.backapi.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    CommentService commentService;
    @Autowired
    UserRepository userRepository;

    /*
     * @Autowired
     * public CommentController(CommentService commentService) {
     * this.commentService = commentService;
     * }
     */

    @GetMapping("/{commentId}/replies")
    public ResponseEntity<List<CommentDto>> getRepliesToComment(@PathVariable Long commentId) {

        return new ResponseEntity<>(commentService.getRepliesToComment(commentId), HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<?> updateComment(@RequestBody CommentDto commentDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        CommentDto updatedCommentDto = commentService.updateComment(commentDto.getId(), commentDto.getContent(), currentUser);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comment updated successfully");
        response.put("post", updatedCommentDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /*@PostMapping
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity<Object> createComment(CommentDto commentDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        return new ResponseEntity<>(commentService.createComment(commentDto, currentUser), HttpStatus.CREATED);
    }*/

    // @PreAuthorize("authenticated")
    @PostMapping
    @PreAuthorize("hasRole('TEACHER') or hasRole('STUDENT')")
    public ResponseEntity<Object> create(@RequestBody @Valid CommentDto commentDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        CommentDto createdCommentDto = commentService.createComment(commentDto, currentUser);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Comment created successfully");
        response.put("post", createdCommentDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
