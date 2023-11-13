package org.project.backapi.service;

import jakarta.persistence.EntityNotFoundException;
import org.project.backapi.converter.UserConverter;
import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.UserDto;
import org.project.backapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private UserRepository userRepository;
    public List<UserDto> getAllUsers() {
        return userConverter.convert(userRepository.findAll());
    }

    public UserDto editUserRole(Long userId, UserDto request) {
        User user = userRepository.findById(userId).orElseThrow(() ->new EntityNotFoundException("user not found"));
        user.setUserRole(request.getUserRole());
        return userConverter.convert(user);
    }
}
