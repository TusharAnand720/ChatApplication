package chatapp.dbManager.table.room;

public interface ITableRoom {

    ItemRoom createItem(String createdBy);

    ItemRoom saveItem(ItemRoom itemRoom, String updatedBy);

    ItemRoom readItem(String roomId);

    void deleteItem(ItemRoom itemRoom, String updatedBy);
}
