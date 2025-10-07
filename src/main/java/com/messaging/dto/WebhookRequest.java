package com.messaging.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public class WebhookRequest {
    private String from;
    private String to;
    private String type;
    private String body;
    private List<String> attachments;
    @JsonProperty("messaging_provider_id")
    private String messagingProviderId;
    @JsonProperty("xillio_id")
    private String xillioId;
    private Instant timestamp;

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

    public String getMessagingProviderId() { return messagingProviderId; }
    public void setMessagingProviderId(String messagingProviderId) { this.messagingProviderId = messagingProviderId; }

    public Instant getTimestamp() { return timestamp; }
    public void setTimestamp(Instant timestamp) { this.timestamp = timestamp; }

    public String getXillioId() { return xillioId; }

    public void setXillioId(String xillioId) { this.xillioId = xillioId; }
}