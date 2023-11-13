package org.project.backapi.controller;

import lombok.RequiredArgsConstructor;
import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.MessageDto;
import org.project.backapi.repository.UserRepository;
import org.project.backapi.service.MessageService;
import org.project.backapi.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    @Autowired
    MessageService messageService;
    @Autowired
    UserRepository userRepository;

    /*@GetMapping("/conversations/{conversationId}")
    public ResponseEntity<?> getAll(
            @PathVariable Long conversationId,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {

        return new ResponseEntity<>(messageService.loadMessage(page, size, conversationId), HttpStatus.OK);

    }*/

    @GetMapping("/{messageId}")
    public ResponseEntity<?> getOne(@PathVariable Long messageId) {

        return new ResponseEntity<>(messageService.searchMessage(messageId), HttpStatus.OK);
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage (@RequestBody MessageDto messageDto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(userDetails.getUsername()).orElseThrow();

        MessageDto createdMessageDto = messageService.sendMessage(messageDto, currentUser);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Message created successfully");
        response.put("Message", createdMessageDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
