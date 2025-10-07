package com.messaging.service.provider;

import com.messaging.entity.Message;
import org.springframework.stereotype.Service;

@Service
public class EmailProviderService implements MessagingProvider {
    @Override
    public void sendMessage(Message message) throws Exception {
        if ("email".equalsIgnoreCase(message.getType())) {
            System.out.println("Mock Email sent: " + message.getBody());
        } else {
            throw new Exception("Unsupported type for Email provider");
        }
    }
}
