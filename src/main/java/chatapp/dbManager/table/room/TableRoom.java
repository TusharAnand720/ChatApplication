package chatapp.dbManager.table.room;

import chatapp.dbManager.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class TableRoom implements ITableRoom{

    private final RoomRepository roomRepository;

    public TableRoom(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public ItemRoom createItem(String createdBy) {
        return new ItemRoom(createdBy);
    }

    @Override
    public ItemRoom saveItem(ItemRoom itemRoom,String updatedBy) {
        itemRoom.setUpdatedBy(updatedBy);
        itemRoom.setUpdatedAt(System.currentTimeMillis());
        roomRepository.save(itemRoom);
        return itemRoom;
    }

    @Override
    public ItemRoom getItem(String roomId) {
        return null;
//        return roomRepository.findById(roomId).get();
    }

    @Override
    public ItemRoom deleteItem(String roomId) {
        return null;
    }
}
