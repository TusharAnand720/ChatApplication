package chatapp.dbManager.table.entityuser;

public interface ITableEntityUser {

    ItemEntityUser createItem(String entityId, String userId, String createdBy);

    ItemEntityUser saveItem(ItemEntityUser itemEntityUser, String updatedBy);

    ItemEntityUser readItem(String roomId, String userId);
}
