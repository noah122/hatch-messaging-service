package com.messaging.service.provider;

import com.messaging.entity.Message;
import org.springframework.stereotype.Service;

@Service
public class SmsProviderService implements MessagingProvider {
    @Override
    public void sendMessage(Message message) throws Exception {
        // simulate provider call
        if ("sms".equalsIgnoreCase(message.getType()) || "mms".equalsIgnoreCase(message.getType())) {
            System.out.println("Mock SMS/MMS sent: " + message.getBody());
        } else {
            throw new Exception("Unsupported type for SMS provider");
        }
    }
}
