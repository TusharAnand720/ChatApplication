package chatapp.dbManager.table.message;

import java.util.List;

public interface ITableMessage {

    ItemMessage createItem(String roomId, String userId, String createdBy);

    ItemMessage saveItem(ItemMessage itemMessage, String updatedBy);

    List<ItemMessage> readItemByPage(int pageNumber, int pageSize, String messageId);

}
