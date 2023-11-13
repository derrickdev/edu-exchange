package org.project.backapi.converter;

import org.project.backapi.domain.Conversation;
import org.project.backapi.domain.Message;
import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.ConversationDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class ConversationConverter {
    public ConversationDto convert (Conversation conversation) {
        ConversationDto dto = new ConversationDto();
        dto.setId(conversation.getId());
        dto.setSenderId(conversation.getInitiator().getId());
        dto.setReceiverId(conversation.getReceiver().getId());
        dto.setMessagesId(conversation.getMessages().stream().map(Message::getId).toList());
        dto.setCreatedAt(conversation.getCreatedAt());
        dto.setUpdatedAt(conversation.getUpdatedAt());

        return dto;
    }

    public Conversation convert(User initiator, User receiver) {
        Conversation conversation = new Conversation();
        conversation.setInitiator(initiator);
        conversation.setReceiver(receiver);
        conversation.setMessages(new HashSet<>());

        return conversation;
    }

    public List<ConversationDto> convert(List<Conversation> conversations) {
        List<ConversationDto> converted = new ArrayList<>();
        for (Conversation conversation : conversations) {
            converted.add(convert(conversation));
        }

        return converted;
    }
}
