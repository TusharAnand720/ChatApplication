package chatapp.dbManager.table.message;

import chatapp.dbManager.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableMessage implements ITableMessage{

    private final MessageRepository messageRepository;

    public TableMessage(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public ItemMessage createItem(String roomId, String userId,String createdBy) {
        return new ItemMessage(roomId,userId,createdBy);

    }

    @Override
    public ItemMessage saveItem(ItemMessage itemMessage, String updatedBy) {
        itemMessage.setUpdatedAt(System.currentTimeMillis());
        itemMessage.setUpdatedBy(updatedBy);
        messageRepository.save(itemMessage);
        return itemMessage;
    }

    @Override
    public List<ItemMessage> getItemByPage(int pageNumber, int pageSize, String messageId) {
//        return messageRepository.getMessage(pageNumber,pageSize,messageId);
        return null;
    }
}
