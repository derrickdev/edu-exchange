package org.project.backapi.repository;

import org.project.backapi.domain.Conversation;
import org.project.backapi.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation ,Long> {
    Page<Conversation> findByMessagesContentContaining(String text, Pageable pageable);

    @Query("SELECT c FROM Conversation c JOIN c.messages m WHERE m.content LIKE %:text% AND (c.initiator = :currentUser OR c.receiver = :currentUser)")
    Page<Conversation> findByMessagesContainingUserInvolved(String text, User currentUser, Pageable pageable);


    @Query("SELECT c FROM Conversation c WHERE c.initiator = :user OR c.receiver = :user")
    Page<Conversation> findByInitiatorOrReceiver(@Param("user") User user, Pageable pageable);

    Optional<Conversation> findByInitiatorAndReceiver(User initiator, User receiver);
}
