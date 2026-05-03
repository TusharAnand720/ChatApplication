package chatapp.room.service;

import authorization.lib.model.JwtClaims;
import chatapp.dbManager.table.room.ItemRoom;
import chatapp.dbManager.table.room.TableRoom;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.room.entity.RoomPayload;
import chatapp.validation.BaseValidator;
import chatapp.validation.ServiceError;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class UpdateRoomRequest implements APIRequest {

    private JwtClaims claims;
    private TableRoom tableRoom;
    private String roomId;
    private RoomPayload roomPayload;

    public UpdateRoomRequest(JwtClaims claims, TableRoom tableRoom, String roomId, RoomPayload roomPayload) {
        this.claims = claims;
        this.tableRoom = tableRoom;
        this.roomId = roomId;
        this.roomPayload = roomPayload;
    }

    @Override
    public ResponseEntity<?> doProcess() {
        try{

            validateRequest(roomPayload);

            ItemRoom itemRoom = tableRoom.getItem(roomId);
            BaseValidator.throwExceptionIfTrue(itemRoom==null, ServiceError.invalid_room_id.getMessage());

            itemRoom.setRoomName(roomPayload.getRoomName());
            itemRoom.setRoomDescription(roomPayload.getRoomDescription());
            tableRoom.saveItem(itemRoom,claims.getSubject());

            HashMap<String,Object> result = new HashMap<>();
            result.put("itemRoom", itemRoom);
            return ServiceResponse.Success(result);
        }catch (Exception e){
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }

    private void validateRequest(RoomPayload roomPayload){
        BaseValidator.throwExceptionIfNotAvailable(roomPayload.getRoomName(), ServiceError.invalid_room_name.getMessage());
    }
}
