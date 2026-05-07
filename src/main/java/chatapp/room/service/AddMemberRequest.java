package chatapp.room.service;

import chatapp.dbManager.table.entityuser.ITableEntityUser;
import chatapp.dbManager.table.entityuser.ItemEntityUser;
import chatapp.dbManager.table.room.ITableRoom;
import chatapp.dbManager.table.room.ItemRoom;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.room.entity.MemberRequest;
import chatapp.utility.constants.Constants;
import chatapp.validation.BaseValidator;
import chatapp.validation.ServiceError;
import chatapp.validation.ServiceException;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class AddMemberRequest implements APIRequest {

    private final MemberRequest memberRequest;
    private final ITableRoom tableRoom;
    private final ITableEntityUser tableEntityUser;

    public AddMemberRequest(MemberRequest memberRequest, ITableRoom tableRoom, ITableEntityUser tableEntityUser) {
        this.memberRequest = memberRequest;
        this.tableRoom = tableRoom;
        this.tableEntityUser = tableEntityUser;
    }

    @Override
    public ResponseEntity<?> doProcess() {
        try {

            validateRoom(memberRequest.getRoomId());

            ItemEntityUser itemEntityUser = tableEntityUser.createItem(memberRequest.getRoomId(), memberRequest.getUserId(), memberRequest.getUserId());
            itemEntityUser.setEntityType(Constants.EntityTypes.ROOM.name());
            tableEntityUser.saveItem(itemEntityUser, memberRequest.getUserId());

            HashMap<String, Object> result = new HashMap<>();
            result.put("member", itemEntityUser);
            return ServiceResponse.Success(result);
        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }

    private void validateRoom(String roomId) throws ServiceException {
        BaseValidator.throwExceptionIfNotAvailable(roomId, ServiceError.invalid_room_id.getMessage());
        ItemRoom itemRoom = tableRoom.readItem(roomId);
        BaseValidator.throwExceptionIfTrue(itemRoom == null, ServiceError.invalid_room.getMessage());
    }
}
