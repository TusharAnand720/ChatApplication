package chatapp.dbManager.table.user;

public interface ITableUser {

    ItemUser createItem(String createdBy);

    ItemUser saveItem(ItemUser itemUser, String updatedBy);

    ItemUser readItemByEmail(String email);

    ItemUser readItem(String userId);
}
