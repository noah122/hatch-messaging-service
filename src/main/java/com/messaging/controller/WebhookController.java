package com.messaging.controller;

import com.messaging.dto.WebhookRequest;
import com.messaging.entity.Message;
import com.messaging.service.ConversationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks")
public class WebhookController {

    private final ConversationService conversationService;

    public WebhookController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping("/sms")
    public Message receiveSmsWebhook(@RequestBody WebhookRequest req) {
        req.setType(req.getType().toLowerCase());
        return conversationService.receiveWebhook(req);
    }

    @PostMapping("/email")
    public Message receiveEmailWebhook(@RequestBody WebhookRequest req) {
        req.setType("email");
        return conversationService.receiveWebhook(req);
    }
}