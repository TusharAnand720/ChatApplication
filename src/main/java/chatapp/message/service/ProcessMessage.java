package chatapp.message.service;

import chatapp.dbManager.table.entityuser.ITableEntityUser;
import chatapp.dbManager.table.entityuser.ItemEntityUser;
import chatapp.dbManager.table.message.ITableMessage;
import chatapp.dbManager.table.message.ItemMessage;
import chatapp.dbManager.table.room.ITableRoom;
import chatapp.dbManager.table.room.ItemRoom;
import chatapp.message.entity.MessageRequest;
import chatapp.validation.BaseValidator;
import chatapp.validation.ServiceError;
import chatapp.validation.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProcessMessage {

    @Autowired
    private ITableMessage tableMessage;
    @Autowired
    private ITableRoom tableRoom;
    @Autowired
    private ITableEntityUser tableEntityUser;

    public void doProcess(MessageRequest messageRequest, String roomId, String userId, SimpMessagingTemplate messagingTemplate) {
        try {

            validateRoomUserMapping(roomId, userId);

            ItemMessage itemMessage = tableMessage.createItem(roomId, userId, userId);
            itemMessage.setContent(messageRequest.getContent());
            tableMessage.saveItem(itemMessage, userId);

            messagingTemplate.convertAndSend("/topic/room/" + roomId, itemMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateRoomUserMapping(String roomId, String userId) throws ServiceException {
        BaseValidator.throwExceptionIfNotAvailable(roomId, ServiceError.invalid_room_id.getMessage());
        BaseValidator.throwExceptionIfNotAvailable(userId, ServiceError.invalid_user_id.getMessage());
        ItemRoom itemRoom = tableRoom.readItem(roomId);
        BaseValidator.throwExceptionIfTrue(itemRoom == null, ServiceError.invalid_room_id.getMessage());
        ItemEntityUser itemEntityUser = tableEntityUser.readItem(roomId, userId);
        BaseValidator.throwExceptionIfTrue(itemEntityUser == null, ServiceError.invalid_user_room.getMessage());
    }
}
