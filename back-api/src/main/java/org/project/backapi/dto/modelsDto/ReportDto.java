package org.project.backapi.dto.modelsDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project.backapi.domain.Post;
import org.project.backapi.domain.User;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
public class ReportDto {
    private Long id;
    @NotBlank(message = "reason is required")
    private String reason;
    private Long reporterId;
    private Long postId;
    private Instant createdAt;

}
