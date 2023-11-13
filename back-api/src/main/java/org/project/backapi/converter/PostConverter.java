package org.project.backapi.converter;

import org.modelmapper.ModelMapper;
import org.project.backapi.domain.Post;
import org.project.backapi.domain.Topic;
import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.PostDto;
import org.project.backapi.enums.PostStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostConverter {
    public PostDto convert(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setContent(post.getContent());
        dto.setImagePaths(post.getImagePaths());
        dto.setUserId(post.getUser().getId());
        dto.setTopicNames(post.getTopics().stream().map(Topic::getName).collect(Collectors.toSet()));
        dto.setTitle(post.getTitle());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());
        dto.setStatus(post.getStatus());
        dto.setHidden(post.getHidden());
        dto.setCommentsCount(CollectionUtils.isEmpty(post.getComments()) ? 0 : post.getComments().size());
        dto.setFullname(post.getUser().getFullname());
        dto.setReportsCount(CollectionUtils.isEmpty(post.getReports()) ? 0 : post.getReports().size());


        return dto;
    }

    public List<PostDto> convert(List<Post> posts) {
        ModelMapper modelMapper = new ModelMapper();
        List<PostDto> converted = new ArrayList<>();
        for (Post post : posts) {
            converted.add(convert(post));
        }

        return converted;
    }

    public Post convert(PostDto dto, User user, List<Topic> topics) {
        Post post = new Post();
        post.setContent(dto.getContent());
        post.setImagePaths(dto.getImagePaths());
        post.setUser(user);
        post.setTopics(topics);
        post.setTitle(dto.getTitle());
        return post;
    }

    public List<Post> convert(List<PostDto> postDtos, User user, List<Topic> topics) {
        List<Post> converted = new ArrayList<>();
        for (PostDto postDto : postDtos) {
            converted.add(convert(postDto, user, topics));
        }

        return converted;
    }
}
