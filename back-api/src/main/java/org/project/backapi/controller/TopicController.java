package org.project.backapi.controller;

import org.project.backapi.domain.Post;
import org.project.backapi.domain.Topic;
import org.project.backapi.dto.modelsDto.PostDto;
import org.project.backapi.dto.modelsDto.TopicDto;
import org.project.backapi.dto.response.PagedResponse;
import org.project.backapi.service.PostService;
import org.project.backapi.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    PostService postService;

    @GetMapping("/{topicName}/posts")
    public ResponseEntity<PagedResponse<PostDto>> getTopicPosts(
            @PathVariable String topicName,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        return new ResponseEntity<>(postService.getTopicPosts(topicName, page, size), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PagedResponse<TopicDto>> getAllTopics(
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        return new ResponseEntity<>(postService.getAllTopics(page, size), HttpStatus.OK);
    }
}
