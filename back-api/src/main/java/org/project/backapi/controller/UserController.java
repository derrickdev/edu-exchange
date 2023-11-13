package org.project.backapi.controller;

import org.project.backapi.service.PostService;
import org.project.backapi.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    PostService postService;

    /*@GetMapping("/{userPseudo}/posts")
    public ResponseEntity<?> getUserPosts(
            @PathVariable String userPseudo,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(postService.getUserPosts(0L, userPseudo, page, size), HttpStatus.OK);
    }*/

    @GetMapping("/{userPseudo}/posts")
    public ResponseEntity<?> getUserPosts(
            @PathVariable String userPseudo,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {

        return new ResponseEntity<>(postService.getUserPosts(userPseudo, page, size), HttpStatus.OK);
    }
}
