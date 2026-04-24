package chatapp.dbManager.repository;

import chatapp.dbManager.table.user.ItemUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<ItemUser, String> {

//    @Query("{ 'email': ?0 }")
    ItemUser findByEmail(String email);
    
}
