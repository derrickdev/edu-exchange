package org.project.backapi.dto.modelsDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.project.backapi.domain.Topic;

@Getter
@Setter
@NoArgsConstructor
public class TopicDto {
    private Long id;
    private String name;

    public TopicDto(Topic topic) {
        this.id = topic.getId();
        this.name = topic.getName();
    }

}
