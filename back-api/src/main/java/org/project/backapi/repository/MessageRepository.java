package org.project.backapi.repository;

import org.project.backapi.domain.Conversation;
import org.project.backapi.domain.Message;
import org.project.backapi.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MessageRepository extends JpaRepository<Message,Long> {
    @Query("SELECT m FROM Message m WHERE m.conversation = :conversation")
    Page<Message> findByConversation(@Param("conversation") Conversation conversation, Pageable pageable);

    Page<Message> findByConversationAndContentContaining(Conversation conversation, String text, Pageable pageable);

    @Query("SELECT m FROM Message m WHERE m.conversation = :conversation AND m.content LIKE %:text% AND (m.conversation.initiator = :currentUser OR m.conversation.receiver = :currentUser)")
    Page<Message> findByConversationContainingUserInvolved(Conversation conversation, String text, User currentUser, Pageable pageable);


}
