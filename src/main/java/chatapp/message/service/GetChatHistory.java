package chatapp.message.service;

import chatapp.dbManager.table.entityuser.ITableEntityUser;
import chatapp.dbManager.table.entityuser.ItemEntityUser;
import chatapp.dbManager.table.message.ITableMessage;
import chatapp.dbManager.table.message.ItemMessage;
import chatapp.dbManager.table.room.ITableRoom;
import chatapp.dbManager.table.room.ItemRoom;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.validation.BaseValidator;
import chatapp.validation.ServiceError;
import chatapp.validation.ServiceException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public class GetChatHistory implements APIRequest {

    private final String roomId;
    private final String userId;
    private final int pageNumber;
    private final int pageSize;
    private final ITableRoom tableRoom;
    private final ITableMessage tableMessage;
    private final ITableEntityUser tableEntityUser;

    public GetChatHistory(String roomId, String userId, int pageNumber, int pageSize, ITableRoom tableRoom, ITableMessage tableMessage, ITableEntityUser tableEntityUser) {
        this.roomId = roomId;
        this.userId = userId;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.tableRoom = tableRoom;
        this.tableMessage = tableMessage;
        this.tableEntityUser = tableEntityUser;
    }

    @Override
    public ResponseEntity<?> doProcess() {
        try {

            validateRequest(roomId, userId);

            Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "createdAt"));
            List<ItemMessage> page = tableMessage.readItemByPage(roomId, pageable);

            HashMap<String, Object> result = new HashMap<>();
            result.put("page", page);
            return ServiceResponse.Success(result);
        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }

    private void validateRequest(String roomId, String userId) throws ServiceException {

        BaseValidator.throwExceptionIfNotAvailable(roomId, ServiceError.invalid_room_id.getMessage());
        BaseValidator.throwExceptionIfNotAvailable(userId, ServiceError.invalid_user_id.getMessage());

        ItemRoom itemRoom = tableRoom.readItem(roomId);
        BaseValidator.throwExceptionIfTrue(itemRoom == null, ServiceError.invalid_room.getMessage());

        ItemEntityUser itemEntityUser = tableEntityUser.readItem(roomId, userId);
        BaseValidator.throwExceptionIfTrue(itemEntityUser == null, ServiceError.invalid_user_room.getMessage());
    }
}
