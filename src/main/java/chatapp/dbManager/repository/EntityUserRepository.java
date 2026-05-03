package chatapp.dbManager.repository;

import chatapp.dbManager.table.entityuser.ItemEntityUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntityUserRepository extends MongoRepository<ItemEntityUser,String> {
}
