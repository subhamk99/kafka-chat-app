package com.example.chatterbox.service;

import com.example.chatterbox.dto.requests.BroadcastMessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ChatService {

    @Autowired
    private KafkaTemplate<String, BroadcastMessageDto> kafkaTemplate;

    @Value("${kafka.topic.chat-message}")
    private String myTopicName;

    public void broadcastMessage(BroadcastMessageDto bmRequest) {

        CompletableFuture<SendResult<String, BroadcastMessageDto>> future = kafkaTemplate.send(myTopicName, bmRequest);
        future.whenComplete((result, throwable) -> {

            if (throwable != null) {
                System.out.println("Message Broadcast Failed!!!");
                throw new RuntimeException(throwable);
            } else {
                System.out.println("Message Broadcast Successful!!!");
            }
        });
    }

    @KafkaListener(topics = "chat", groupId = "chat-receiver")
    public void listenGroupChat(BroadcastMessageDto broadcast) {
        System.out.println("Received Message in chat group: " + broadcast.message() + " by "+ broadcast.userName());
    }
}
