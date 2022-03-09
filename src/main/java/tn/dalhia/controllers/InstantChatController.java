package tn.dalhia.controllers;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import tn.dalhia.entities.Message;

@Controller
public class InstantChatController {

    @MessageMapping("/hello")
    @SendTo("/ws")
    public Message broadcastNews(@Payload Message message) {
        try {
            Thread.sleep(1000); // simulated delay
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("ghghghghhghghghg");
        System.err.println(message.toString());
        return message;
    }
}
