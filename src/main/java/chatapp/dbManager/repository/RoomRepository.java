package chatapp.dbManager.repository;

import chatapp.dbManager.table.room.ItemRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends MongoRepository<ItemRoom,String> {

    @Query("")
    ItemRoom findByRoomId(String roomId);
}
