package com.messaging.controller;

import com.messaging.dto.WebhookRequest;
import com.messaging.entity.Message;
import com.messaging.service.MessagingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/webhooks")
public class WebhookController {

    private final MessagingService service;

    public WebhookController(MessagingService service) {
        this.service = service;
    }

    @PostMapping("/sms")
    public Message receiveSmsWebhook(@RequestBody WebhookRequest req) {
        Message msg = new Message();
        msg.setFromAddress(req.getFrom());
        msg.setToAddress(req.getTo());
        msg.setType(req.getType());
        msg.setBody(req.getBody());
        msg.setAttachments(req.getAttachments());
        msg.setProviderId(req.getMessagingProviderId());
        msg.setTimestamp(req.getTimestamp());
        return service.saveMessage(msg);
    }

    @PostMapping("/email")
    public Message receiveEmailWebhook(@RequestBody WebhookRequest req) {
        Message msg = new Message();
        msg.setFromAddress(req.getFrom());
        msg.setToAddress(req.getTo());
        msg.setType("email");
        msg.setBody(req.getBody());
        msg.setAttachments(req.getAttachments());
        msg.setProviderId(req.getXillioId());
        msg.setTimestamp(req.getTimestamp());
        return service.saveMessage(msg);
    }
}