package org.project.backapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
}
