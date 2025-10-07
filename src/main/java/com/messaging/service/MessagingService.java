package com.messaging.service;

import com.messaging.entity.Conversation;
import com.messaging.entity.Message;
import com.messaging.repository.ConversationRepository;
import com.messaging.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class MessagingService {

    private final ConversationRepository conversationRepo;
    private final MessageRepository messageRepo;

    public MessagingService(ConversationRepository conversationRepo, MessageRepository messageRepo) {
        this.conversationRepo = conversationRepo;
        this.messageRepo = messageRepo;
    }

    public Message saveMessage(Message message) {
        Conversation convo = conversationRepo
                .findByParticipantAAndParticipantB(message.getFromAddress(), message.getToAddress())
                .or(() -> conversationRepo.findByParticipantAAndParticipantB(message.getToAddress(), message.getFromAddress()))
                .orElseGet(() -> {
                    Conversation c = new Conversation();
                    c.setParticipantA(message.getFromAddress());
                    c.setParticipantB(message.getToAddress());
                    return conversationRepo.save(c);
                });

        message.setConversation(convo);

        if (message.getTimestamp() == null) {
            message.setTimestamp(Instant.now());
        }

        return messageRepo.save(message);
    }

    public List<Conversation> getAllConversations() {
        return conversationRepo.findAll();
    }

    public List<Message> getMessagesForConversation(Long conversationId) {
        return messageRepo.findByConversationId(conversationId);
    }
}