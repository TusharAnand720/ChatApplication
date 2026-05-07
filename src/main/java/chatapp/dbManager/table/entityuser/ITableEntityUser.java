package chatapp.dbManager.table.entityuser;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ITableEntityUser {

    ItemEntityUser createItem(String entityId, String userId, String createdBy);

    ItemEntityUser saveItem(ItemEntityUser itemEntityUser, String updatedBy);

    ItemEntityUser readItem(String roomId, String userId);

    List<ItemEntityUser> readItemByUser(String userId);

    boolean deleteItem(String roomId, String userId, String deletedBy);

    List<ItemEntityUser> readItemByPage(String roomId, Pageable pageable);
}
