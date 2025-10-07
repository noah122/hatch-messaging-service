package com.messaging.controller;

import com.messaging.dto.MessageRequest;
import com.messaging.entity.Message;
import com.messaging.service.MessagingService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessagingService service;

    public MessageController(MessagingService service) {
        this.service = service;
    }

    @PostMapping("/sms")
    public Message sendSmsOrMms(@RequestBody MessageRequest req) {
        //message.setType(message.getType().toLowerCase()); // sms or mms
        Message msg = new Message();
        msg.setFromAddress(req.getFrom());
        msg.setToAddress(req.getTo());
        msg.setType("email");
        msg.setBody(req.getBody());
        msg.setAttachments(req.getAttachments());
        msg.setType(req.getType().toLowerCase());
        msg.setTimestamp(req.getTimestamp());
        return service.saveMessage(msg);
    }

    @PostMapping("/email")
    public Message sendEmail(@RequestBody MessageRequest req) {
        Message msg = new Message();
        msg.setFromAddress(req.getFrom());
        msg.setToAddress(req.getTo());
        msg.setType("email");
        msg.setBody(req.getBody());
        msg.setAttachments(req.getAttachments());
        msg.setType(req.getType().toLowerCase());
        msg.setTimestamp(req.getTimestamp());
        return service.saveMessage(msg);
    }
}