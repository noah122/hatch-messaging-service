package com.messaging.service.provider;

import com.messaging.entity.Message;

public interface MessagingProvider {

    void sendMessage(Message message) throws Exception;

}
