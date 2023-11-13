package org.project.backapi.dto.modelsDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Getter
@Setter
public class MessageDto {
    private Long id;
    @NotBlank(message = "the message content is required")
    private String content;
    private Instant createdAt;
    private Instant updatedAt;
    private Long authorId;
    @NotBlank(message = "please provide receiverId")
    private Long receiverId;
    private Long conversationId;
}

