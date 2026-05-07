package chatapp.room.service;

import chatapp.dbManager.table.entityuser.ITableEntityUser;
import chatapp.dbManager.table.entityuser.ItemEntityUser;
import chatapp.dbManager.table.room.ITableRoom;
import chatapp.dbManager.table.room.ItemRoom;
import chatapp.dbManager.table.user.ITableUser;
import chatapp.dbManager.table.user.ItemUser;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.room.entity.MemberResponse;
import chatapp.validation.BaseValidator;
import chatapp.validation.ServiceError;
import chatapp.validation.ServiceException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetRoomMembersRequest implements APIRequest {

    private String roomId;
    private int pageNumber;
    private int pageSize;
    private ITableEntityUser tableEntityUser;
    private ITableRoom tableRoom;
    private ITableUser tableUser;

    public GetRoomMembersRequest(String roomId, int pageNumber, int pageSize, ITableEntityUser tableEntityUser, ITableRoom tableRoom, ITableUser tableUser) {
        this.roomId = roomId;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.tableEntityUser = tableEntityUser;
        this.tableRoom = tableRoom;
        this.tableUser = tableUser;
    }

    @Override
    public ResponseEntity<?> doProcess() {
        try {

            validateRequest(roomId);

            if (pageSize > 10 || pageSize < 1) {
                pageSize = 10;
            }

            Pageable pageable = PageRequest.of(
                    pageNumber - 1,
                    pageSize,
                    Sort.by(Sort.Direction.DESC, "createdAt")
            );
            List<ItemEntityUser> itemEntityUserList = tableEntityUser.readItemByPage(roomId, pageable);

            List<MemberResponse> roomMemberResponses = new ArrayList<>();
            for (ItemEntityUser itemEntityUser : itemEntityUserList) {
                ItemUser itemUser = tableUser.readItem(itemEntityUser.getUserId());
                MemberResponse roomMemberResponse = new MemberResponse();
                roomMemberResponse.setUserId(itemUser.getUserId());
                roomMemberResponse.setEmail(itemUser.getEmail());
                roomMemberResponse.setFirstName(itemUser.getFirstName());
                roomMemberResponse.setLastName(itemUser.getLastName());
                roomMemberResponses.add(roomMemberResponse);
            }


            HashMap<String, Object> result = new HashMap<>();
            result.put("members", roomMemberResponses);
            return ServiceResponse.Success(result);

        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }

    private void validateRequest(String roomId) throws ServiceException {
        BaseValidator.throwExceptionIfNotAvailable(roomId, ServiceError.invalid_room_id.getMessage());
        ItemRoom itemRoom = tableRoom.readItem(roomId);
        BaseValidator.throwExceptionIfTrue(itemRoom == null, ServiceError.invalid_room.getMessage());
    }

}
