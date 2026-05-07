package chatapp.dbManager.repository;

import chatapp.dbManager.table.message.ItemMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends MongoRepository<ItemMessage, String> {

//    @Query()
//    List<ItemMessage> getMessage(int pageNumber, int pageSize, String messageId);

    @Query("{ 'roomId' : ?0 }")
    Page<ItemMessage> getChatHistory(String roomId, Pageable pageable);
}
