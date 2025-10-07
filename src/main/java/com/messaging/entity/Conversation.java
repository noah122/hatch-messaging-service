package com.messaging.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conversations")
public class Conversation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String participantA;
    private String participantB;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Message> messages = new ArrayList<>();

    public Conversation() {}

    public Conversation(String participantA, String participantB) {
        this.participantA = participantA;
        this.participantB = participantB;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getParticipantA() { return participantA; }
    public void setParticipantA(String participantA) { this.participantA = participantA; }

    public String getParticipantB() { return participantB; }
    public void setParticipantB(String participantB) { this.participantB = participantB; }

    public List<Message> getMessages() { return messages; }
    public void setMessages(List<Message> messages) { this.messages = messages; }

    // Add a message and set the relationship
    public void addMessage(Message msg) {
        messages.add(msg);
        msg.setConversation(this);
    }
}