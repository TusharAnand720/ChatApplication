package chatapp.dbManager.repository;

import chatapp.dbManager.table.entityuser.ItemEntityUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntityUserRepository extends MongoRepository<ItemEntityUser, String> {

    @Query("{ 'entityId' : ?0 }")
    Page<ItemEntityUser> findMembersByPage(String roomId, Pageable pageable);
}
