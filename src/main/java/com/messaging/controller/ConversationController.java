package com.messaging.controller;

import com.messaging.entity.Conversation;
import com.messaging.entity.Message;
import com.messaging.service.MessagingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final MessagingService service;

    public ConversationController(MessagingService service) {
        this.service = service;
    }

    @GetMapping
    public List<Conversation> getConversations() {
        return service.getAllConversations();
    }

    @GetMapping("/{id}/messages")
    public List<Message> getMessages(@PathVariable Long id) {
        return service.getMessagesForConversation(id);
    }
}