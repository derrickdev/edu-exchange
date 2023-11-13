package org.project.backapi.dto.modelsDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CommentDto {
    private Long id;
    @NotBlank(message = "Content is required")
    private String content;
    private List<String> imagePaths;
    private Long authorId;
    @NotNull(message = "Post ID is required")
    private Long postId;
    private Long parentId;
    private List<CommentDto> replies;
    private int votesCount;

}
