package com.example.chatterbox.controllers;

import com.example.chatterbox.dto.requests.BroadcastMessageDto;
import com.example.chatterbox.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/chat")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping("/broadcast")
    public ResponseEntity<?> broadcast(@RequestBody BroadcastMessageDto bmRequest){
        chatService.broadcastMessage(bmRequest);

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }

    @PostMapping("/listen")
    public ResponseEntity<?> broadcast(){

        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}
