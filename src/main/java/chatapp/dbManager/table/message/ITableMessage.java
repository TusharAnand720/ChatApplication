package chatapp.dbManager.table.message;

public interface ITableMessage {

    ItemMessage createItem(String createdBy);
    ItemMessage saveItem(ItemMessage itemMessage,String updatedBy);
    ItemMessage getItemByPage(int pageNumber,int pageSize, String messageId);

}
