package chatapp.dbManager.table.message;

public class TableMessage implements ITableMessage{
    @Override
    public ItemMessage createItem(String createdBy) {
        return null;
    }

    @Override
    public ItemMessage saveItem(ItemMessage itemMessage, String updatedBy) {
        return null;
    }

    @Override
    public ItemMessage getItemByPage(int pageNumber, int pageSize, String messageId) {
        return null;
    }
}
