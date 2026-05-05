package chatapp.dbManager.table.entityuser;

import chatapp.dbManager.repository.EntityUserRepository;
import org.springframework.stereotype.Service;

@Service
public class TableEntityUser implements ITableEntityUser {

    private final EntityUserRepository entityUserRepository;

    public TableEntityUser(EntityUserRepository entityUserRepository) {
        this.entityUserRepository = entityUserRepository;
    }

    @Override
    public ItemEntityUser createItem(String entityId, String userId, String createdBy) {
        return new ItemEntityUser(entityId, userId, createdBy);
    }

    @Override
    public ItemEntityUser saveItem(ItemEntityUser itemEntityUser, String updatedBy) {
        itemEntityUser.setUpdatedAt(System.currentTimeMillis());
        itemEntityUser.setUpdatedBy(updatedBy);
        entityUserRepository.save(itemEntityUser);
        return itemEntityUser;
    }

    @Override
    public ItemEntityUser readItem(String roomId, String userId) {
//        return entityUserRepository.findItem(roomId, userId);
        return null;
    }
}
