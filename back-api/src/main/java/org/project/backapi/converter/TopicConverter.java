package org.project.backapi.converter;

import org.modelmapper.ModelMapper;
import org.project.backapi.domain.Topic;
import org.project.backapi.dto.modelsDto.TopicDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TopicConverter {
    public TopicDto convert(Topic topic) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(topic, TopicDto.class);
    }

    public List<TopicDto> convert(List<Topic> topics) {
        ModelMapper modelMapper = new ModelMapper();
        List<TopicDto> converted = new ArrayList<>();
        for (Topic topic : topics) {
            converted.add(convert(topic));
        }
        return converted;
    }
}
