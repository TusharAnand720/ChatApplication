package chatapp.room.entity;

public class RoomPayload {

    private String roomName;
    private String roomLogo;
    private String roomDescription;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomLogo() {
        return roomLogo;
    }

    public void setRoomLogo(String roomLogo) {
        this.roomLogo = roomLogo;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }
}
