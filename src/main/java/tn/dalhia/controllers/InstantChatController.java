package tn.dalhia.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import tn.dalhia.entities.Message;

@Controller
public class InstantChatController {

    @MessageMapping("/g")
    @SendTo("/general")
    public Message broadcastNews(@Payload Message message) {
        return message;
    }
}
