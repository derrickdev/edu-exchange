package org.project.backapi.service;

import org.project.backapi.converter.ConversationConverter;
import org.project.backapi.converter.MessageConverter;
import org.project.backapi.domain.Conversation;
import org.project.backapi.domain.Message;
import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.ConversationDto;
import org.project.backapi.dto.modelsDto.MessageDto;
import org.project.backapi.dto.response.PagedResponse;
import org.project.backapi.exception.RessourceNotFoundException;
import org.project.backapi.repository.ConversationRepository;
import org.project.backapi.repository.MessageRepository;
import org.project.backapi.repository.UserRepository;
import org.project.backapi.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private MessageConverter messageConverter;
    @Autowired
    ConversationRepository conversationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ConversationConverter conversationConverter;

        public PagedResponse<MessageDto>loadMessage (int page, int size, Long conversationId) {
            AppUtils.validatePageNumberAndSize(page, size);

            Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(
                    () -> new RessourceNotFoundException(String.format("Conversation: %d, does not exist", conversationId)));

            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
            Page<Message> message = messageRepository.findByConversation(conversation, pageable);

            if (message.isEmpty()) {
                return new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 0, true);
            }
            List<MessageDto> dtos = messageConverter.convert(message.getContent());

            return new PagedResponse<>(dtos, message.getNumber(), message.getSize(), message.getTotalElements(), message.getTotalPages(), message.isLast());

        }

        public MessageDto searchMessage(Long messageId) {
            Message message = messageRepository.findById(messageId).orElseThrow(
                    () -> new RessourceNotFoundException(String.format("Message: %d, does not exist", messageId)));

            return messageConverter.convert(message);
        }
        public  MessageDto sendMessage (MessageDto messageDto, User sender) {
            Long conversationId = messageDto.getConversationId();
            Long receiverId = messageDto.getReceiverId();
            Conversation conversation = createConversation(conversationId, sender, receiverId);


            Message message = messageRepository.save(messageConverter.convert(messageDto, conversation));
            assert conversation != null;
            conversation.getMessages().add(message);
            conversation = conversationRepository.save(conversation);

            return messageConverter.convert(message);
        }

        public PagedResponse<MessageDto>loadMessage (int page, int size) {
            AppUtils.validatePageNumberAndSize(page, size);
            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "name");
            Page<Message> message = messageRepository.findAll(pageable);

            if (message.getNumberOfElements() == 0) {
                return new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 0, true);
            }
            List<MessageDto> dtos = messageConverter.convert(message.getContent());

            return new PagedResponse<>(dtos, message.getNumber(), message.getSize(), message.getTotalElements(), message.getTotalPages(), message.isLast());
        }

        private Conversation createConversation(Long conversationId, User sender, Long receiverId) {
            Conversation conversation = null;
            if(conversationId == null){
                User receiver = userRepository.findById(receiverId).orElseThrow(
                        () -> new RessourceNotFoundException(String.format("You can not send messages to absent user: %d", receiverId)));

                // Find the existing conversation between the sender and receiver
                Optional<Conversation> existingConversation = conversationRepository.findByInitiatorAndReceiver(sender, receiver)
                        .or(() -> conversationRepository.findByInitiatorAndReceiver(receiver, sender));

                if (existingConversation.isPresent()) {
                    conversation = existingConversation.get();
                } else {
                    conversation = conversationConverter.convert(sender, receiver);
                    conversation = conversationRepository.save(conversation);
                }
            }
            else {
                conversation = conversationRepository.findById(conversationId).orElseThrow(
                        () -> new RessourceNotFoundException(String.format("Conversation: %d, does not exist", conversationId)));
            }
            return conversation;
        }

        public PagedResponse<ConversationDto> getMyConversations(Integer page, Integer size, User currentUser) {
            AppUtils.validatePageNumberAndSize(page, size);

            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "updatedAt");
            Page<Conversation> conversation = conversationRepository.findByInitiatorOrReceiver(currentUser, pageable);

            if(conversation.isEmpty()) {
                return new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 0, true);
            }

            List<ConversationDto> dtos = conversationConverter.convert(conversation.getContent());

            return new PagedResponse<>(dtos, conversation.getNumber(), conversation.getSize(), conversation.getTotalElements(), conversation.getTotalPages(), conversation.isLast());
        }

        public PagedResponse<MessageDto> searchMessagesInConversation(Integer page, Integer size, Long conversationId, String text, User currentUser) {
            AppUtils.validatePageNumberAndSize(page, size);

            Conversation conversation = conversationRepository.findById(conversationId).orElseThrow(
                    () -> new RessourceNotFoundException(String.format("Conversation: %d, does not exist", conversationId)));

            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "updatedAt");
            Page<Message> message = messageRepository.findByConversationContainingUserInvolved(conversation, text, currentUser, pageable);

            if (message.isEmpty()) {
                return new PagedResponse<>(Collections.emptyList(), 0, 0, 0, 0, true);
            }
            List<MessageDto> dtos = messageConverter.convert(message.getContent());

            return new PagedResponse<>(dtos, message.getNumber(), message.getSize(), message.getTotalElements(), message.getTotalPages(), message.isLast());

        }

        public PagedResponse<ConversationDto> searchConversationsByMessageContent(Integer page, Integer size, String text, User currentUser) {
            AppUtils.validatePageNumberAndSize(page, size);

            Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "updatedAt");
            Page<Conversation> conversation = conversationRepository.findByMessagesContainingUserInvolved(text, currentUser, pageable);

            List<ConversationDto> dtos = conversationConverter.convert(conversation.getContent());

            return new PagedResponse<>(dtos, conversation.getNumber(), conversation.getSize(), conversation.getTotalElements(), conversation.getTotalPages(), conversation.isLast());

        }

    }
