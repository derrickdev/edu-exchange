package org.project.backapi.controller;

import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.ConversationDto;
import org.project.backapi.dto.modelsDto.MessageDto;
import org.project.backapi.dto.response.PagedResponse;
import org.project.backapi.repository.UserRepository;
import org.project.backapi.service.MessageService;
import org.project.backapi.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/conversations")
public class ConversationController {
    @Autowired
    MessageService messageService;
    @Autowired
    UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> allConversations(
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User: %s, not found", userDetails.getUsername())));

        return new ResponseEntity<>(messageService.getMyConversations(page, size, currentUser), HttpStatus.OK);
    }
    @GetMapping("/{conversationId}")
    public ResponseEntity<?> getAllMessages(
            @PathVariable Long conversationId,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {

        return new ResponseEntity<>(messageService.loadMessage(page, size, conversationId), HttpStatus.OK);

    }

    @GetMapping("/{conversationId}/search/{text}/")
    public PagedResponse<MessageDto> searchMessagesInConversation(
            @PathVariable Long conversationId,
            @PathVariable String text,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User: %s, not found", userDetails.getUsername())));

        return messageService.searchMessagesInConversation(page, size, conversationId, text, currentUser);
    }

    @GetMapping("/search/{text}")
    public PagedResponse<ConversationDto> searchConversationsByContent(
            @PathVariable String text,
            @RequestParam(name = "page", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) Integer page,
            @RequestParam(name = "size", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) Integer size) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userRepository.findByEmail(userDetails.getUsername()).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User: %s, not found", userDetails.getUsername())));

        return messageService.searchConversationsByMessageContent(page, size,  text, currentUser);
    }
}
