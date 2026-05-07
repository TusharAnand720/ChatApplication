package chatapp.room.service;

import authentication.lib.model.JwtClaims;
import chatapp.dbManager.table.entityuser.ITableEntityUser;
import chatapp.dbManager.table.room.ITableRoom;
import chatapp.dbManager.table.room.ItemRoom;
import chatapp.dbManager.table.user.ITableUser;
import chatapp.dbManager.table.user.ItemUser;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.room.entity.MemberRequest;
import chatapp.validation.BaseValidator;
import chatapp.validation.ServiceError;
import chatapp.validation.ServiceException;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class DeleteMemberRequest implements APIRequest {

    private final JwtClaims claims;
    private final MemberRequest memberRequest;
    private final ITableRoom tableRoom;
    private final ITableUser tableUser;
    private final ITableEntityUser tableEntityUser;

    public DeleteMemberRequest(JwtClaims claims,MemberRequest memberRequest, ITableRoom tableRoom, ITableUser tableUser, ITableEntityUser tableEntityUser) {
        this.claims = claims;
        this.memberRequest = memberRequest;
        this.tableRoom = tableRoom;
        this.tableUser = tableUser;
        this.tableEntityUser = tableEntityUser;
    }

    @Override
    public ResponseEntity<?> doProcess() {
        try{

            validateRequest(memberRequest);

           boolean isDeleted = tableEntityUser.deleteItem(memberRequest.getRoomId(),memberRequest.getUserId(),claims.getSubject());

            HashMap<String , Object> result = new HashMap<>();
            result.put("isDeleted",isDeleted);
            return ServiceResponse.Success(result);

        }catch (Exception e){
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }

    private void validateRequest(MemberRequest memberRequest) throws ServiceException {
        BaseValidator.throwExceptionIfNotAvailable(memberRequest.getRoomId(),ServiceError.invalid_room_id.getMessage());
        BaseValidator.throwExceptionIfNotAvailable(memberRequest.getUserId(),ServiceError.invalid_user_id.getMessage());
        ItemRoom itemRoom = tableRoom.readItem(memberRequest.getRoomId());
        BaseValidator.throwExceptionIfTrue(itemRoom==null,ServiceError.invalid_room.getMessage());
        ItemUser itemUser = tableUser.readItem(memberRequest.getUserId());
        BaseValidator.throwExceptionIfTrue(itemUser==null,ServiceError.invalid_user_id.getMessage());
    }
}
