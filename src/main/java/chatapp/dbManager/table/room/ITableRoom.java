package chatapp.dbManager.table.room;

public interface ITableRoom {

    ItemRoom createItem(String createdBy);

    ItemRoom saveItem(String createdBy, ItemRoom itemRoom);

    ItemRoom getItemByRoomId(String roomId);
    
}
