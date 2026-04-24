package chatapp.dbManager.table.user;

public interface ITableUser {

    ItemUser createItem(String createdBy);

    ItemUser saveItem(ItemUser itemUser,String updatedBy);

    ItemUser getUserByEmail(String email);
}
