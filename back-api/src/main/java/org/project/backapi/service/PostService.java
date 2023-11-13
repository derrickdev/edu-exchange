package org.project.backapi.service;

import org.project.backapi.converter.PostConverter;
import org.project.backapi.converter.TopicConverter;
import org.project.backapi.domain.Post;
import org.project.backapi.domain.Topic;
import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.PostDto;
import org.project.backapi.dto.modelsDto.TopicDto;
import org.project.backapi.dto.response.PagedResponse;
import org.project.backapi.enums.PostStatus;
import org.project.backapi.exception.ForbiddenAccessOperation;
import org.project.backapi.exception.RejectedOperationException;
import org.project.backapi.exception.RequestNotAuthorizedException;
import org.project.backapi.exception.RessourceNotFoundException;
import org.project.backapi.repository.PostRepository;
import org.project.backapi.repository.TopicRepository;
import org.project.backapi.repository.UserRepository;
import org.project.backapi.utils.AppConstants;
import org.project.backapi.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    TopicConverter topicConverter;
    @Autowired
    PostConverter postConverter;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TopicRepository topicRepository;

    /*public PostDto createPost(PostDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(
                        () -> new RessourceNotFoundException(String.format("User: %d, not found", dto.getUserId())));

        List<Topic> topics = dto.getTopicNames().stream().map(name -> topicRepository.findByName(name).orElseGet(() -> {
            Topic newTopic = new Topic();
            newTopic.setName(name);
            return topicRepository.save(newTopic);
        })).collect(Collectors.toList());

        Post post = postConverter.convert(dto, user, topics);
        post = postRepository.save(post);

        return postConverter.convert(post);
    }*/

    public PostDto createPost(PostDto dto, User currentUser) {
        List<Topic> topics = dto.getTopicNames().stream().map(name -> topicRepository.findByName(name).orElseGet(() -> {
            Topic newTopic = new Topic();
            newTopic.setName(name);
            return topicRepository.save(newTopic);
        })).collect(Collectors.toList());

        Post post = postConverter.convert(dto, currentUser, topics);
        post = postRepository.save(post);

        return postConverter.convert(post);
    }

    /*public PostDto updatePost(PostDto dto) {
        Post post = postRepository.findById(dto.getId())
                .orElseThrow(
                        () -> new RessourceNotFoundException(String.format("Post: %d does not exist", dto.getId())));

        if (!Objects.equals(post.getUser().getId(), dto.getUserId())) {
            throw new RequestNotAuthorizedException(String.format(
                    "User: %d is not the author of post: %d, operation rejected", dto.getUserId(), dto.getId()));
        }

        post.setContent(dto.getContent());
        post.setImagePaths(dto.getImagePaths());
        post.setTopics(dto.getTopicNames().stream().map(name -> topicRepository.findByName(name).orElseGet(() -> {
            Topic newTopic = new Topic();
            newTopic.setName(name);
            return topicRepository.save(newTopic);
        })).collect(Collectors.toList()));

        post = postRepository.save(post);

        return postConverter.convert(post);
    }*/

    public PostDto updatePost(PostDto dto, Long postId, User currentUser) {
        Post post = postRepository.findById(postId)
                .orElseThrow(
                        () -> new RessourceNotFoundException(String.format("Post: %d does not exist", postId)));

        if(post.getHidden()) {
            throw new ForbiddenAccessOperation("You do not have privileges to access this resource");
        }

        if(post.getStatus().equals(PostStatus.CLOSED)) {
            throw new RequestNotAuthorizedException("You can not proceed with this request cause this post has been closed");
        }

        if (!Objects.equals(post.getUser().getId(), currentUser.getId())) {
            throw new RequestNotAuthorizedException(String.format(AppConstants.YOU_ARE_NOT_THE_AUTHOR_OF_THIS + " post"));
        }

        post.setContent(dto.getContent());
        post.setImagePaths(dto.getImagePaths());
        post.setTopics(dto.getTopicNames().stream().map(name -> topicRepository.findByName(name).orElseGet(() -> {
            Topic newTopic = new Topic();
            newTopic.setName(name);
            return topicRepository.save(newTopic);
        })).collect(Collectors.toList()));

        post = postRepository.save(post);

        return postConverter.convert(post);
    }

    public PostDto hidePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RessourceNotFoundException(String.format("Post: %d not found", postId))
        );
        if (post.getHidden().equals(true)) {
            throw new RejectedOperationException(String.format("The post: %d, has already been hidden", postId));
        }
        post.setHidden(true);
        post = postRepository.save(post);

        return postConverter.convert(post);
    }

    public PagedResponse<TopicDto> getAllTopics(int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "name");
        Page<Topic> topics = topicRepository.findAll(pageable);

        if (topics.getNumberOfElements() == 0) {
            return new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 0, true);
        }
        List<TopicDto> dtos = topicConverter.convert(topics.getContent());

        return new PagedResponse<>(dtos, topics.getNumber(), topics.getSize(), topics.getTotalElements(), topics.getTotalPages(), topics.isLast());
    }

    public PagedResponse<PostDto> getAll(int page, int size, boolean onlyVisible) {
        AppUtils.validatePageNumberAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Post> posts;
        if(!onlyVisible) {
            posts = postRepository.findAll(pageable);
        } else {
            posts = postRepository.findByHiddenFalse(pageable);
        }

        if (posts.isEmpty()) {
            return new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 0, true);
        }
        List<PostDto> dtos = postConverter.convert(posts.getContent());

        return new PagedResponse<>(dtos, posts.getNumber(), posts.getSize(), posts.getTotalElements(), posts.getTotalPages(), posts.isLast());
    }

    public PostDto getOne(Long postId) {
        return postConverter.convert(postRepository.findById(postId)
                .orElseThrow(() -> new RessourceNotFoundException(String.format("Post: %d not found", postId))));
    }

    public PagedResponse<PostDto> getUserPosts(String pseudo, int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);
        User user = userRepository.findByPseudo(pseudo).orElseThrow();
        Set<Post> setPosts = user.getPosts();

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Post> posts = postRepository.findAll(pageable);
        if (posts.isEmpty()) {
            return new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 0, true);
        }

        List<PostDto> dtos = postConverter.convert(posts.getContent());

        return new PagedResponse<>(dtos, posts.getNumber(), posts.getSize(), posts.getTotalElements(), posts.getTotalPages(), posts.isLast());
    }

    public PagedResponse<PostDto> getTopicPosts(String topicName, int page, int size) {
        AppUtils.validatePageNumberAndSize(page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "title");
        Topic topic = topicRepository.findByName(topicName)
                .orElseThrow(() -> new RessourceNotFoundException(String.format("Topic: %s, does not exist", topicName)));

        Page<Post> posts = postRepository.findByTopicsName(topicName, pageable);

        if (posts.isEmpty()) {
            return new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 0, true);
        }
        List<PostDto> dtos = postConverter.convert(posts.getContent());

        return new PagedResponse<>(dtos, posts.getNumber(), posts.getSize(), posts.getTotalElements(), posts.getTotalPages(), posts.isLast());
    }

    public PostDto closePost(Long postId, User currentUser) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new RessourceNotFoundException(String.format("Post: %d not found", postId))
        );
        if (post.getStatus().equals(PostStatus.CLOSED)) {
            throw new RejectedOperationException(String.format("The post: %d, has already been closed", postId));
        }
        post.setStatus(PostStatus.CLOSED);
        post = postRepository.save(post);

        return postConverter.convert(post);
    }

    public PagedResponse<PostDto> getByPostContentOrTitle(Integer page, Integer size, String text) {
        AppUtils.validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Post> posts = postRepository.findByTitleOrContent(text, text, pageable);

        if (posts.isEmpty()) {
            return new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 0, true);
        }
        List<PostDto> dtos = postConverter.convert(posts.getContent());

        return new PagedResponse<>(dtos, posts.getNumber(), posts.getSize(), posts.getTotalElements(), posts.getTotalPages(), posts.isLast());
    }

    public List<PostDto> search(String q){
        String query = "%" + q + "%";
        return postConverter.convert(postRepository.searchPost(query));
    }
}
