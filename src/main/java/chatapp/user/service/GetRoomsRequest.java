package chatapp.user.service;

import chatapp.dbManager.table.entityuser.ITableEntityUser;
import chatapp.dbManager.table.entityuser.ItemEntityUser;
import chatapp.dbManager.table.room.ITableRoom;
import chatapp.dbManager.table.room.ItemRoom;
import chatapp.dbManager.table.user.ITableUser;
import chatapp.middleware.APIRequest;
import chatapp.middleware.ServiceResponse;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetRoomsRequest implements APIRequest {

    private final String userId;
    private final ITableUser tableUser;
    private final ITableRoom tableRoom;
    private final ITableEntityUser tableEntityUser;

    public GetRoomsRequest(String userId, ITableUser tableUser, ITableRoom tableRoom, ITableEntityUser tableEntityUser) {
        this.userId = userId;
        this.tableUser = tableUser;
        this.tableRoom = tableRoom;
        this.tableEntityUser = tableEntityUser;
    }

    @Override
    public ResponseEntity<?> doProcess() {
        try {

            List<ItemRoom> rooms = new ArrayList<>();
            List<ItemEntityUser> entityUsers = tableEntityUser.readItemByUser(userId);
            for (ItemEntityUser itemEntityUser : entityUsers) {
                ItemRoom room = tableRoom.readItem(itemEntityUser.getEntityId());
                if (room != null) {
                    rooms.add(room);
                }
            }
            HashMap<String, Object> result = new HashMap<>();
            result.put("rooms", rooms);
            return ServiceResponse.Success(result);

        } catch (Exception e) {
            return ServiceResponse.BadRequest(e.getMessage());
        }
    }
}
