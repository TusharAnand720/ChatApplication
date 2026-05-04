package chatapp.message.service;

import chatapp.dbManager.table.message.ItemMessage;
import chatapp.dbManager.table.message.TableMessage;
import chatapp.message.entity.MessageRequest;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProcessMessage {


    public void doProcess(MessageRequest messageRequest,String roomId, SimpMessagingTemplate messagingTemplate, TableMessage tableMessage){
        try{

            ItemMessage itemMessage = tableMessage.createItem(roomId,messageRequest.getUserId(),messageRequest.getUserId());
            itemMessage.setContent(messageRequest.getContent());
            tableMessage.saveItem(itemMessage,messageRequest.getUserId());

            messagingTemplate.convertAndSend("/topic/room/"+roomId,itemMessage);
            System.out.println("Message process completed");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
