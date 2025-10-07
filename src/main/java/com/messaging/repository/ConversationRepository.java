package com.messaging.repository;

import com.messaging.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    Optional<Conversation> findByParticipantAAndParticipantB(String participantA, String participantB);
    Optional<Conversation> findByParticipantBAndParticipantA(String participantB, String participantA);
}
