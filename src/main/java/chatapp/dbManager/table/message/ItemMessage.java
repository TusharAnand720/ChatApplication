package chatapp.dbManager.table.message;

import chatapp.dbManager.table.AuditFields;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "Message")
public class ItemMessage extends AuditFields {

    @Id
    @Field("compositeKey")
    private String compositeKey; // roomId-userId
    private String roomId;
    private String userId;
    private String content;

    private ItemMessage(String roomId , String userId , String createdAt){
        super(createdAt,System.currentTimeMillis());
        this.roomId = roomId;
        this.userId = userId;
        this.compositeKey = roomId+"-"+userId;
    }

    public String getCompositeKey() {
        return compositeKey;
    }

    public void setCompositeKey(String compositeKey) {
        this.compositeKey = compositeKey;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
