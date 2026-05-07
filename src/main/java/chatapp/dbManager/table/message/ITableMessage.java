package chatapp.dbManager.table.message;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITableMessage {

    ItemMessage createItem(String roomId, String userId, String createdBy);

    ItemMessage saveItem(ItemMessage itemMessage, String updatedBy);

    List<ItemMessage> readItemByPage(String roomId, Pageable pageable);

}
