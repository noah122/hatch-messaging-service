package com.messaging.dto;

import java.time.Instant;
import java.util.List;

public class MessageRequest {

    private String from;
    private String to;
    private String type; // sms, mms, email
    private String body;
    private List<String> attachments;
    private Instant timestamp;

    // No-arg constructor required by Jackson
    public MessageRequest() {}

    // Getters and setters
    public String getFrom() { return from; }
    public void setFrom(String from) { this.from = from; }

    public String getTo() { return to; }
    public void setTo(String to) { this.to = to; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getBody() { return body; }
    public void setBody(String body) { this.body = body; }

    public List<String> getAttachments() { return attachments; }
    public void setAttachments(List<String> attachments) { this.attachments = attachments; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }
}