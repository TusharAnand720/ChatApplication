package chatapp.dbManager.repository;

import chatapp.dbManager.table.room.ItemRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<ItemRoom, String> {
    
}
