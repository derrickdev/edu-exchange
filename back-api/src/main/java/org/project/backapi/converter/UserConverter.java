package org.project.backapi.converter;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDto convert(User user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDto.class);
    }

    public List<UserDto> convert(List<User> users) {
        ModelMapper modelMapper = new ModelMapper();
        List<UserDto> converted = new ArrayList<>();
        for (User user : users) {
            converted.add(convert(user));
        }
        return converted;
    }
}
