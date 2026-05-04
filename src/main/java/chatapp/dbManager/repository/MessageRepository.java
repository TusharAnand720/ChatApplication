package chatapp.dbManager.repository;

import chatapp.dbManager.table.message.ItemMessage;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<ItemMessage,String> {

//    @Query()
//    List<ItemMessage> getMessage(int pageNumber, int pageSize, String messageId);
}
