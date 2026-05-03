package chatapp.room.service;

import authorization.lib.model.JwtClaims;
import chatapp.utility.constants.Constants;
import chatapp.dbManager.table.entityuser.ItemEntityUser;
import chatapp.dbManager.table.entityuser.TableEntityUser;
import chatapp.dbManager.table.room.ItemRoom;
import chatapp.dbManager.table.room.TableRoom;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.room.entity.RoomPayload;
import chatapp.validation.BaseValidator;
import chatapp.validation.ServiceError;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class CreateRoomRequest implements APIRequest {

    private final RoomPayload roomPayload;
    private final TableRoom tableRoom;
    private final TableEntityUser entityUser;
    private final JwtClaims claims;

    public CreateRoomRequest(JwtClaims claims, RoomPayload roomPayload, TableRoom tableRoom,TableEntityUser entityUser) {
        this.roomPayload = roomPayload;
        this.tableRoom = tableRoom;
        this.entityUser = entityUser;
        this.claims=claims;
    }

    @Override
    public ResponseEntity<?> doProcess() {
        try{

            validateRequest(roomPayload);

            ItemRoom itemRoom = tableRoom.createItem(claims.getSubject());
            itemRoom.setRoomDescription(roomPayload.getRoomDescription());
            itemRoom.setRoomName(roomPayload.getRoomName());
            itemRoom = tableRoom.saveItem(itemRoom,claims.getSubject());

            ItemEntityUser itemEntityUser = entityUser.createItem(itemRoom.getRoomId(), itemRoom.getCreatedBy(), Constants.Commons.ED.getName());
            itemEntityUser.setEntityType(Constants.EntityTypes.ROOM.name());
            entityUser.saveItem(itemEntityUser,Constants.Commons.ED.getName());

            HashMap<String,Object> result = new HashMap<>();
            result.put("itemRoom",itemRoom);

            return ServiceResponse.Success(result);
        }catch (Exception e){
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }

    private void validateRequest(RoomPayload roomPayload){
        BaseValidator.throwExceptionIfNotAvailable(roomPayload.getRoomName(), ServiceError.invalid_room_name.getMessage());
    }
}
