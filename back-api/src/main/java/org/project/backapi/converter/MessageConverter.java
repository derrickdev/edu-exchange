package org.project.backapi.converter;

import org.modelmapper.ModelMapper;
import org.project.backapi.domain.Conversation;
import org.project.backapi.domain.Message;
import org.project.backapi.domain.User;
import org.project.backapi.dto.modelsDto.MessageDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MessageConverter {
    public MessageDto convert(Message message) {
        MessageDto dto = new MessageDto();
        dto.setId(message.getId());
        dto.setContent(message.getContent());
        dto.setCreatedAt(message.getCreatedAt());
        dto.setUpdatedAt(message.getUpdatedAt());
        dto.setAuthorId(message.getConversation().getInitiator().getId());
        dto.setConversationId(message.getConversation().getId());
        dto.setReceiverId(message.getConversation().getReceiver().getId());
        return dto ;
    }
    public Message convert (MessageDto messageDto , Conversation conversation) {
        Message message = new Message();
        message.setContent(messageDto.getContent());
        message.setConversation(conversation);

        return message;
    }


    public List<MessageDto> convert(List<Message> messages) {
        ModelMapper modelMapper = new ModelMapper();
        List<MessageDto> converted = new ArrayList<>();
        for (Message message : messages) {
            converted.add(convert(message));
        }
        return converted;
    }
}
