package chatapp.dbManager.table.room;

public interface ITableRoom {

    ItemRoom createItem(String createdBy);

    ItemRoom saveItem(ItemRoom itemRoom,String updatedBy);

    ItemRoom getItem(String roomId);

    void deleteItem(ItemRoom itemRoom,String updatedBy);
}
