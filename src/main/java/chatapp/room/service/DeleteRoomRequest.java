package chatapp.room.service;

import authentication.lib.model.JwtClaims;
import chatapp.dbManager.table.room.ItemRoom;
import chatapp.dbManager.table.room.TableRoom;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import chatapp.validation.BaseValidator;
import chatapp.validation.ServiceError;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class DeleteRoomRequest implements APIRequest {

    private JwtClaims claims;
    private String roomId;
    private TableRoom tableRoom;

    public DeleteRoomRequest(JwtClaims claims, String roomId, TableRoom tableRoom) {
        this.claims = claims;
        this.roomId = roomId;
        this.tableRoom = tableRoom;
    }

    @Override
    public ResponseEntity<?> doProcess() {
        try {
            ItemRoom itemRoom = tableRoom.readItem(roomId);
            BaseValidator.throwExceptionIfTrue(itemRoom == null, ServiceError.invalid_room_id.getMessage());

            tableRoom.deleteItem(itemRoom, claims.getSubject());

            HashMap<String, Object> result = new HashMap<>();
            result.put("isDeleted", true);
            return ServiceResponse.Success(result);
        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }
}
