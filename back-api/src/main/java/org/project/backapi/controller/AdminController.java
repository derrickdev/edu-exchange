package org.project.backapi.controller;

import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.UserDto;
import org.project.backapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PutMapping("/user/{userId}/edit-role")
    public ResponseEntity<UserDto> editUserRole(@PathVariable Long userId, @RequestBody UserDto request) {
        return new ResponseEntity<>(userService.editUserRole(userId, request), HttpStatus.OK);
    }
}
