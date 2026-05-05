package chatapp.dbManager.table.user;

import chatapp.dbManager.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class TableUser implements ITableUser {

    private final UserRepository userRepository;

    public TableUser(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ItemUser createItem(String createdBy) {
        return new ItemUser(createdBy);
    }

    @Override
    public ItemUser saveItem(ItemUser itemUser, String updatedBy) {
        itemUser.setUpdatedBy(updatedBy);
        itemUser.setUpdatedAt(System.currentTimeMillis());
        userRepository.save(itemUser);
        return itemUser;
    }

    @Override
    public ItemUser readItemByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public ItemUser readItem(String userId) {
        return userRepository.findByUserId(userId);
    }
}
