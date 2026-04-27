package chatapp.dbManager.table.room;

import chatapp.dbManager.table.AuditFields;
import chatapp.utility.helper.ServiceHelper;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Room")
public class TableRoom extends AuditFields {
    @Id
    @Field("roomId")
    private String roomId;
    private String roomLogo;
    private String roomName;
    private String roomDescription;

    public TableRoom(String createdBy) {
        super(createdBy, System.currentTimeMillis());
        this.roomId = ServiceHelper.createId("ROOM");
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomLogo() {
        return roomLogo;
    }

    public void setRoomLogo(String roomLogo) {
        this.roomLogo = roomLogo;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }
}
