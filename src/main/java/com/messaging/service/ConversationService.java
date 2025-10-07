package com.messaging.service;

import com.messaging.dto.MessageRequest;
import com.messaging.dto.WebhookRequest;
import com.messaging.entity.Conversation;
import com.messaging.entity.Message;
import com.messaging.repository.ConversationRepository;
import com.messaging.repository.MessageRepository;
import com.messaging.service.provider.EmailProviderService;
import com.messaging.service.provider.SmsProviderService;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepo;
    private final MessageRepository messageRepo;
    private final SmsProviderService smsProvider;
    private final EmailProviderService emailProvider;

    public ConversationService(ConversationRepository cRepo,
                               MessageRepository mRepo,
                               SmsProviderService sms,
                               EmailProviderService email) {
        this.conversationRepo = cRepo;
        this.messageRepo = mRepo;
        this.smsProvider = sms;
        this.emailProvider = email;
    }

    public Message sendMessage(MessageRequest req) throws Exception {
        Message msg = new Message();
        msg.setFromAddress(req.getFrom());
        msg.setToAddress(req.getTo());
        msg.setType(req.getType());
        msg.setBody(req.getBody());
        msg.setAttachments(req.getAttachments());
        msg.setTimestamp(req.getTimestamp() != null ? req.getTimestamp() : Instant.now());

        Conversation conv = conversationRepo.findByParticipantAAndParticipantB(req.getFrom(), req.getTo())
                .or(() -> conversationRepo.findByParticipantBAndParticipantA(req.getFrom(), req.getTo()))
                .orElseGet(() -> {
                    Conversation newConv = new Conversation(req.getFrom(), req.getTo());
                    return conversationRepo.save(newConv);
                });

        conv.addMessage(msg);
        conversationRepo.save(conv);

        if ("sms".equalsIgnoreCase(req.getType()) || "mms".equalsIgnoreCase(req.getType())) {
            smsProvider.sendMessage(msg);
        } else if ("email".equalsIgnoreCase(req.getType())) {
            emailProvider.sendMessage(msg);
        }

        return messageRepo.save(msg);
    }

    public Message receiveWebhook(WebhookRequest req) {
        Message msg = new Message();
        msg.setFromAddress(req.getFrom());
        msg.setToAddress(req.getTo());
        msg.setType(req.getType());
        msg.setBody(req.getBody());
        msg.setAttachments(req.getAttachments());
        msg.setProviderId(req.getMessagingProviderId());
        msg.setTimestamp(req.getTimestamp() != null ? req.getTimestamp() : Instant.now());

        Conversation conv = conversationRepo.findByParticipantAAndParticipantB(req.getFrom(), req.getTo())
                .or(() -> conversationRepo.findByParticipantBAndParticipantA(req.getFrom(), req.getTo()))
                .orElseGet(() -> {
                    Conversation newConv = new Conversation(req.getFrom(), req.getTo());
                    return conversationRepo.save(newConv);
                });

        conv.addMessage(msg);
        conversationRepo.save(conv);

        return messageRepo.save(msg);
    }
}