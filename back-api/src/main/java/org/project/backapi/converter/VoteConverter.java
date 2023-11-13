package org.project.backapi.converter;

import org.modelmapper.ModelMapper;
import org.project.backapi.domain.Vote;
import org.project.backapi.dto.modelsDto.VoteDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class VoteConverter {
    public static VoteDto convert(Vote vote) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(vote, VoteDto.class);
    }

    public List<VoteDto> convert(List<Vote> votes) {
        ModelMapper modelMapper = new ModelMapper();
        List<VoteDto> converted = new ArrayList<>();
        for (Vote vote : votes) {
            converted.add(convert(vote));
        }
        return converted;
    }
}
