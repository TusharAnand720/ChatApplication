package chatapp.message.controller;

import chatapp.dbManager.table.message.ITableMessage;
import chatapp.message.entity.MessageRequest;
import chatapp.message.service.ProcessMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class MessageController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private ITableMessage tableMessage;

    @Autowired
    private ProcessMessage processMessage;


    @MessageMapping("/chat/{roomId}")
    public void sendMessage(@DestinationVariable String roomId, @Payload MessageRequest messageRequest, Principal principal) {
        try {
//            String senderId = principal.getName();
            processMessage.doProcess(messageRequest, roomId, "senderId", messagingTemplate);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @MessageMapping("/chat/join/{roomId}")
    public void userJoined(@DestinationVariable String roomId, Principal principal) {

    }

}
