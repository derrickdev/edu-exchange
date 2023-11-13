package org.project.backapi.dto.modelsDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
public class ConversationDto {
    private Long id;
    private Long senderId;
    @NotBlank(message = "Define the receiver")
    private Long receiverId;
    private List<Long> messagesId;
    private Instant createdAt;
    private Instant updatedAt;

}
