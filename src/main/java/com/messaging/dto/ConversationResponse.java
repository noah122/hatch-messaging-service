package com.messaging.dto;

import com.messaging.entity.Message;
import java.util.List;

public class ConversationResponse {
    public Long conversationId;
    public String participantA;
    public String participantB;
    public List<Message> messages;

    public ConversationResponse(Long id, String a, String b, List<Message> msgs) {
        this.conversationId = id;
        this.participantA = a;
        this.participantB = b;
        this.messages = msgs;
    }
}
