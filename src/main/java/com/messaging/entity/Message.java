package com.messaging.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromAddress;
    private String toAddress;
    private String type; // sms, mms, email
    private String body;

    @ElementCollection
    private List<String> attachments;

    private String providerId; // messaging_provider_id or xillio_id
    private Instant timestamp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "conversation_id")
    @JsonBackReference
    private Conversation conversation;

    // No-arg constructor (required by JPA)
    public Message() {}

    // All-args constructor
    public Message(Long id, String fromAddress, String toAddress, String type, String body,
                   List<String> attachments, String providerId, Instant timestamp,
                   Conversation conversation) {
        this.id = id;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.type = type;
        this.body = body;
        this.attachments = attachments;
        this.providerId = providerId;
        this.timestamp = timestamp;
        this.conversation = conversation;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFromAddress() { return fromAddress; }
    public void setFromAddress(String fromAddress) { this.fromAddress = fromAddress; }

    public String getToAddress() { return toAddress; }
    public void setToAddress(String toAddress) { this.toAddress = toAddress; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public List<String> getAttachments() { return attachments; }
    public void setAttachments(List<String> attachments) { this.attachments = attachments; }

    public String getProviderId() { return providerId; }
    public void setProviderId(String providerId) { this.providerId = providerId; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public Conversation getConversation() { return conversation; }
    public void setConversation(Conversation conversation) { this.conversation = conversation; }
}