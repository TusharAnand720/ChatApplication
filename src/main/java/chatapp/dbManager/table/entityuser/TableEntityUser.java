package chatapp.dbManager.table.entityuser;

import chatapp.dbManager.repository.EntityUserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TableEntityUser implements ITableEntityUser {

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

    @Override
    public boolean deleteItem(String roomId, String userId, String deletedBy) {
        ItemEntityUser entityUser = readItem(roomId,userId);
        if(entityUser==null){
            return false;
        }else {
            entityUser.setActive(false);
            saveItem(entityUser,deletedBy);
            return true;
        }
    }

    @Override
    public List<ItemEntityUser> readItemByPage(String roomId, Pageable pageable) {
        Page<ItemEntityUser> page = entityUserRepository.findMembersByPage(roomId, pageable);
        return page.getContent();
    }
}
